package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ProfileViewController {

    private final ProfileRepository profileRepository;

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model) {
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        model.addAttribute("profile", profile);
        return "profile";
    }
}