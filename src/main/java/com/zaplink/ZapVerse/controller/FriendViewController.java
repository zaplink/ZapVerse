package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.*;
import com.zaplink.ZapVerse.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class FriendViewController {
    private final ProfileRepository profileRepository;
    private final FriendRequestRepository friendRequestRepository;

    // 1. Friend Suggestions (Paginated)
    @GetMapping("/friends/suggestions")
    public String friendSuggestions(@AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Profile current = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        Set<Profile> friends = current.getFriends();
        List<Profile> all = profileRepository.findAll();
        // Exclude self, friends, and users with pending requests
        List<Profile> suggestions = all.stream()
                .filter(p -> p.getId() != current.getId())
                .filter(p -> !friends.contains(p))
                .filter(p -> friendRequestRepository
                        .findByProfileAndReceiverAndStatus(current, p, RequestStatus.PENDING).isEmpty())
                .collect(Collectors.toList());
        int from = page * size, to = Math.min(from + size, suggestions.size());
        model.addAttribute("suggestions", suggestions.subList(from, to));
        model.addAttribute("page", page);
        model.addAttribute("totalPages", (suggestions.size() + size - 1) / size);
        return "Friend-suggestions";
    }

    // 2. Send Friend Request
    @PostMapping("/friends/request")
    public String sendRequest(@AuthenticationPrincipal User user, @RequestParam int receiverId) {
        Profile sender = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        if (sender.getId() == receiverId)
            return "redirect:/friends/suggestions";
        Profile receiver = profileRepository.findById(receiverId).orElseThrow();
        // Prevent duplicate
        if (!friendRequestRepository.findByProfileAndReceiverAndStatus(sender, receiver, RequestStatus.PENDING)
                .isEmpty())
            return "redirect:/friends/suggestions";
        if (sender.getFriends().contains(receiver))
            return "redirect:/friends/suggestions";
        FriendRequest req = new FriendRequest();
        req.setProfile(sender);
        req.setReceiver(receiver);
        req.setStatus(RequestStatus.PENDING);
        friendRequestRepository.save(req);
        return "redirect:/friends/suggestions";
    }

    // 3. Remove Friend Request (Cancel)
    @PostMapping("/friends/remove-request")
    public String removeRequest(@AuthenticationPrincipal User user, @RequestParam int receiverId) {
        Profile sender = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        Profile receiver = profileRepository.findById(receiverId).orElseThrow();
        friendRequestRepository.deleteAll(
                friendRequestRepository.findByProfileAndReceiverAndStatus(sender, receiver, RequestStatus.PENDING));
        return "redirect:/friends/suggestions";
    }

    // 4. View Incoming & Outgoing Requests
    @GetMapping("/friends/requests")
    public String friendRequests(@AuthenticationPrincipal User user, Model model) {
        Profile current = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        List<FriendRequest> incoming = friendRequestRepository.findByReceiverAndStatus(current, RequestStatus.PENDING);
        List<FriendRequest> outgoing = friendRequestRepository.findByProfileAndStatus(current, RequestStatus.PENDING);
        model.addAttribute("incoming", incoming);
        model.addAttribute("outgoing", outgoing);
        return "Friend-request";
    }

    // 5. Accept Request
    @PostMapping("/friends/accept")
    public String acceptRequest(@AuthenticationPrincipal User user, @RequestParam int requestId) {
        FriendRequest req = friendRequestRepository.findById(requestId).orElseThrow();
        Profile current = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        if (req.getReceiver().getId() != current.getId())
            return "redirect:/friends/requests";
        req.setStatus(RequestStatus.ACCEPTED);
        // Add each other as friends
        current.getFriends().add(req.getProfile());
        req.getProfile().getFriends().add(current);
        profileRepository.save(current);
        profileRepository.save(req.getProfile());
        friendRequestRepository.save(req);
        return "redirect:/friends/requests";
    }

    // 6. Decline Request
    @PostMapping("/friends/decline")
    public String declineRequest(@AuthenticationPrincipal User user, @RequestParam int requestId) {
        FriendRequest req = friendRequestRepository.findById(requestId).orElseThrow();
        Profile current = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        if (req.getReceiver().getId() != current.getId())
            return "redirect:/friends/requests";
        req.setStatus(RequestStatus.DECLINED);
        friendRequestRepository.save(req);
        return "redirect:/friends/requests";
    }

    // 7. View All Friends
    @GetMapping("/friends/all")
    public String allFriends(@AuthenticationPrincipal User user, Model model) {
        Profile current = profileRepository.findByEmail(user.getUsername()).orElse(null);
        if (current == null)
            return "redirect:/login";
        Set<Profile> friends = current.getFriends();

        // Map friendId -> mutual friends count
        Map<Integer, Integer> mutualCounts = new HashMap<>();
        for (Profile friend : friends) {
            Set<Profile> theirFriends = friend.getFriends();
            // Exclude current user and the friend themself
            int mutual = (int) theirFriends.stream()
                    .filter(f -> friends.contains(f) && f.getId() != friend.getId())
                    .count();
            mutualCounts.put(friend.getId(), mutual);
        }

        model.addAttribute("friends", friends);
        model.addAttribute("mutualCounts", mutualCounts);
        model.addAttribute("activePage", "friends"); // for friends pages
        return "All-friends";
    }
    // ...existing code...

    // 8. Unfriend (Remove Friend)
    @PostMapping("/friends/unfriend")
    public String unfriend(@AuthenticationPrincipal User user, @RequestParam int friendId) {
        Profile current = profileRepository.findByEmail(user.getUsername()).orElseThrow();
        Profile friend = profileRepository.findById(friendId).orElseThrow();

        // Remove each other from friends list
        current.getFriends().remove(friend);
        friend.getFriends().remove(current);

        profileRepository.save(current);
        profileRepository.save(friend);

        // After unfriending, redirect to all friends (the UI will show them in
        // suggestions)
        return "redirect:/friends/all";
    }

    // ...existing code...
}