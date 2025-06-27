package com.zaplink.ZapVerse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedViewController {

    @GetMapping("/feed")
    public String displayFeed(Model model) {
        return "feed";
    }
}
