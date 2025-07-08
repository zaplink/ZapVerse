package com.zaplink.ZapVerse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class FeedViewController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/feed")
    public String displayFeed(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Profile profile = profileRepository.findByEmail(email).orElse(null);
        String displayName = (profile != null) ? (profile.getFname() + " " + profile.getLname()) : "User";
        String avatar = (profile != null && profile.getAvatar() != null && !profile.getAvatar().isEmpty()) ? profile.getAvatar() : "felix.png";
        model.addAttribute("sidebarAvatar", "/avatars/" + avatar);
        model.addAttribute("sidebarName", displayName);
        model.addAttribute("sidebarEmail", email);
        return "feed";
    }
}
