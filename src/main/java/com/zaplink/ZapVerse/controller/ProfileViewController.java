package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "profile-edit";
    }

    @PostMapping("/profile/edit")
    public String editProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam String email,
            @RequestParam String avatar) {
        Profile profile = profileRepository.findByEmail(user.getUsername()).orElse(null);
        if (profile != null) {
            profile.setFname(fname);
            profile.setLname(lname);
            profile.setEmail(email);
            profile.setAvatar(avatar);
            profileRepository.save(profile);
        }
        return "redirect:/profile";
    }
}