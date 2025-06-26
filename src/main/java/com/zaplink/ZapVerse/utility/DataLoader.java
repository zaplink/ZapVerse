package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.React;
import com.zaplink.ZapVerse.model.ReactionType;
import com.zaplink.ZapVerse.repository.LikeRespository;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataLoader {

    private ProfileRepository profileRepository;
    private PostRepository postRepository;
    private LikeRespository likeRespository;

    @Autowired
    public DataLoader(ProfileRepository profileRepository, PostRepository postRepository, LikeRespository likeRespository) {
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
        this.likeRespository = likeRespository;
    }

    @PostConstruct
    private void init() {

        Profile profile1 = new Profile();
        profile1.setFname("Ada");
        profile1.setLname("Lovelace");
        profile1.setEmail("ada@zapverse.dev");
        profile1.setPassword("analytical123");
        profile1 = profileRepository.save(profile1);

        Profile profile2 = new Profile();
        profile2.setFname("Alan");
        profile2.setLname("Turing");
        profile2.setEmail("alan@zapverse.dev");
        profile2.setPassword("enigma123");
        profile2 = profileRepository.save(profile2);

        Post post = new Post();
        post.setTopic("Computing Vision");
        post.setContent("Envisioned the first algorithm way before computers existed.");
        post.setProfile(profile1);
        post = postRepository.save(post);

        profile1.setPosts(List.of(post));
        profileRepository.save(profile1);

        React react = new React();
        react.setPost(post);
        react.setReaction(ReactionType.LIKE);
        react.setProfile(profile2);
        likeRespository.save(react);

        System.out.println("\nData saved to profile\n");
    }
}
