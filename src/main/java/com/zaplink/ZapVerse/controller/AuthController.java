package com.zaplink.ZapVerse.controller;

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

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String avatar,
                           Model model) {
        try {
            profileService.registerUser(email, password, firstName, lastName, avatar);
            model.addAttribute("success", "Registration successful! You can now login.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred. Please try again.");
        }
        return "register";
    }

    @GetMapping("/register")
    public String registerPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "register";
    }
}
