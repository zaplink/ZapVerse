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
    public ResponseEntity<String> toggleLike(@RequestBody ReactDTO dto) {
        Optional<Post> postOpt = postRepository.findById(dto.getPostId());
        Optional<Profile> profileOpt = profileRepository.findById(dto.getProfileId());

        if (postOpt.isEmpty() || profileOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Post or Profile not found");
        }

        Post post = postOpt.get();
        Profile profile = profileOpt.get();

        Optional<React> existing = reactRepository.findByPostAndProfile(post, profile);

        boolean liked = "LOVE".equals(dto.getReaction());

        if (liked) {
            if (existing.isEmpty()) {
                React react = new React();
                react.setPost(post);
                react.setProfile(profile);
                react.setLiked(true);
                reactRepository.save(react);
            }
            return ResponseEntity.ok("Liked");
        } else {
            existing.ifPresent(reactRepository::delete);
            return ResponseEntity.ok("Unliked");
        }
    }
}
