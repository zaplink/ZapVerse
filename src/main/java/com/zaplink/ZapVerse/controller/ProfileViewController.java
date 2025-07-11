package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProfileViewController {

    private final ProfileRepository profileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model) {
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        model.addAttribute("profile", profile);

        // Fetch posts by this profile and sort by createdAt descending
        List<Post> posts = profile != null ? profile.getPosts() : List.of();
        posts = posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt,
                        Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .toList();

        model.addAttribute("posts", posts);
        model.addAttribute("activePage", "profile"); // For Profile page
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfileForm(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "redirect:/login";
        }
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        if (profile == null) {
            model.addAttribute("error", "Profile not found.");
            return "profile";
        }
        model.addAttribute("profile", profile);
        // Pass avatar list
        model.addAttribute("avatars", List.of(
                "arthur.png", "ava.png", "cleo.png", "dante.png", "eli.png", "eliza.png", "felix.png", "grant.png",
                "jayden.png", "jonas.png", "lana.png", "layla.png", "liam.png", "malik.png", "mei.png", "milo.png",
                "naomi.png", "noah.png", "omar.png"));
        model.addAttribute("activePage", "profile"); // For Profile page
        return "profile-edit";
    }

    @PostMapping("/profile/edit")
    public String editProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam String avatar,
            @RequestParam(required = false) String oldPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String confirmPassword,
            Model model) {
        // Fetch profile by email (unique) and get its ID
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        if (profile == null) {
            model.addAttribute("error", "Profile not found.");
            return "profile-edit";
        }

        // Update fields
        profile.setFname(fname);
        profile.setLname(lname);
        profile.setAvatar(avatar);

        // Handle password change if requested
        if (oldPassword != null && !oldPassword.isBlank() && newPassword != null && !newPassword.isBlank()) {
            if (!passwordEncoder.matches(oldPassword, profile.getPassword())) {
                model.addAttribute("error", "Current password is incorrect.");
                model.addAttribute("profile", profile);
                model.addAttribute("avatars", List.of(
                        "arthur.png", "ava.png", "cleo.png", "dante.png", "eli.png", "eliza.png", "felix.png",
                        "grant.png",
                        "jayden.png", "jonas.png", "lana.png", "layla.png", "liam.png", "malik.png", "mei.png",
                        "milo.png",
                        "naomi.png", "noah.png", "omar.png"));
                return "profile-edit";
            }
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("error", "New passwords do not match.");
                model.addAttribute("profile", profile);
                model.addAttribute("avatars", List.of(
                        "arthur.png", "ava.png", "cleo.png", "dante.png", "eli.png", "eliza.png", "felix.png",
                        "grant.png",
                        "jayden.png", "jonas.png", "lana.png", "layla.png", "liam.png", "malik.png", "mei.png",
                        "milo.png",
                        "naomi.png", "noah.png", "omar.png"));
                return "profile-edit";
            }
            profile.setPassword(passwordEncoder.encode(newPassword));
        }

        // Save the updated profile (by ID, not creating new)
        profileRepository.save(profile);
        model.addAttribute("activePage", "profile"); // for profile.html
        return "redirect:/profile";
    }

    @PostMapping("/profile/post/delete")
    public String deletePost(@RequestParam Integer postId, @AuthenticationPrincipal User user) {
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        if (profile != null) {
            profile.getPosts().removeIf(p -> p.getId() == postId);
            profileRepository.save(profile);
        }
        // Optionally, use a PostRepository to delete by id
        // postRepository.deleteById(postId);
        return "redirect:/profile";
    }

    @PostMapping("/profile/post/edit")
    public String editPost(
            @RequestParam Integer postId,
            @RequestParam String topic,
            @RequestParam String content,
            @AuthenticationPrincipal User user,
            Model model) {
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        if (profile != null) {
            for (Post p : profile.getPosts()) {
                if (p.getId() == postId) {
                    p.setTopic(topic);
                    p.setContent(content);
                    break;
                }
            }
            profileRepository.save(profile);
        }
        return "redirect:/profile";
    }
}