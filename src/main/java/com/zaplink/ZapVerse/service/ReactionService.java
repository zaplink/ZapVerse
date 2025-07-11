package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.dto.ReactDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.React;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.repository.ReactRespository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReactionService {

    private final ReactRespository reactionRepository;
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;

    public ReactionService(ReactRespository reactionRepository, PostRepository postRepository, ProfileRepository profileRepository) {
        this.reactionRepository = reactionRepository;
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
    }

    public void reactToPost(ReactDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // If reaction is null, delete existing reaction
        Optional<React> existing = reactionRepository.findByPostAndProfile(post, profile);

        if (dto.getReaction() == null || dto.getReaction().isEmpty()) {
            existing.ifPresent(reactionRepository::delete);
            updateLoveCount(post);
            return;
        }

        React reaction = existing.orElse(new React());
              reaction.setPost(post);
               reaction.setProfile(profile);
        reaction.setReaction(dto.getReaction());

        reactionRepository.save(reaction);

        updateLoveCount(post);
    }
    private void updateLoveCount(Post post) {
        long loveCount = reactionRepository.countByPostAndReaction(post, "LOVE");
        post.setLoveCount(loveCount);
        postRepository.save(post);
    }


}




