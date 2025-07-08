package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.dto.ReactDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.React;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.repository.ReactRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/react")
public class ReactController {

    private final ReactRespository reactRepository;
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;

    public ReactController(ReactRespository reactRepository, PostRepository postRepository, ProfileRepository profileRepository) {
        this.reactRepository = reactRepository;
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
    }
    @PostMapping
    public ResponseEntity<String> toggleReaction(@RequestBody ReactDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Optional<React> existing = reactRepository.findByPostAndProfile(post, profile);

        if (dto.getReaction() == null || dto.getReaction().isEmpty()) {
            // Remove reaction
            existing.ifPresent(reactRepository::delete);
            return ResponseEntity.ok("Reaction removed");
        }

        // Add or update reaction
        React react = existing.orElse(new React());
        react.setPost(post);
        react.setProfile(profile);
        react.setReaction(dto.getReaction());

        reactRepository.save(react);
        return ResponseEntity.ok("Reaction saved: " + dto.getReaction());
    }

}
