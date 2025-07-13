package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.dto.ReactDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.React;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.repository.ReactRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Transactional
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
        } else {
            React react = existing.orElse(new React());
            react.setPost(post);
            react.setProfile(profile);
            react.setReaction(dto.getReaction());

            reactRepository.save(react);
            reactRepository.flush();
        }

        long loveCount = reactRepository.countByPostAndReaction(post, "LOVE");
        post.setLoveCount(loveCount);

        postRepository.save(post);
        post.setModifiedAt(LocalDateTime.now());
        postRepository.save(post);
        return ResponseEntity.ok(String.valueOf(loveCount));

    }

}
