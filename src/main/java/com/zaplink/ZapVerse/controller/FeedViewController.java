package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.utility.PostMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class FeedViewController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;

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

        if (profile != null) {
            model.addAttribute("profileId", profile.getId()); // ✅ Inject here
        }

        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(); // or modifiedAt if you prefer
        List<PostDTO> postDTOs = posts.stream().map(PostMapper::toDTO).toList();
        model.addAttribute("posts", postDTOs);

        return "feed";
    }
}
