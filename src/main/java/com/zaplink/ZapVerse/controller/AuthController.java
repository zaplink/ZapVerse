package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthController {

    ProfileService profileService;

    @GetMapping("/")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", "Invalid email or password");
        }
        return "login";

    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            Profile userProfile = profileService.authenticateUser(email, password);
            model.addAttribute("user", userProfile);
            return "redirect:/feed"; // Redirect to feed page
        } catch (Exception e) {
            return "redirect:/?error=true";
        }
    }

}
