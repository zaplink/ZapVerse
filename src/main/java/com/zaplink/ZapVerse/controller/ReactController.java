package com.zaplink.ZapVerse.controller;


import com.zaplink.ZapVerse.dto.ReactDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.React;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.repository.ReactRespository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/react")
@AllArgsConstructor
public class ReactController {

    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final ReactRespository reactRepository;

    @PostMapping
    public ResponseEntity<String> addReaction(@RequestBody ReactDTO dto) {
        Post post = postRepository.findById(dto.getPostId()).orElse(null);
        Profile profile = profileRepository.findById(dto.getProfileId()).orElse(null);

        if (post == null || profile == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid post or profile ID.");
        }

        // Optional: Update reaction if already exists
        React react = new React();
        react.setPost(post);
        react.setProfile(profile);
        react.setReaction(dto.getReaction());

        reactRepository.save(react);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reaction added.");
    }

    @GetMapping("/post/{postId}")
    public List<React> getReactionsForPost(@PathVariable int postId) {
        return reactRepository.findAll().stream()
                .filter(r -> r.getPost().getId() == postId)
                .toList();
    }










}
