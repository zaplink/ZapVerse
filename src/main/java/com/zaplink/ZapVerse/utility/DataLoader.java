package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.model.*;
import com.zaplink.ZapVerse.repository.ReactRespository;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DataLoader {

    private ProfileRepository profileRepository;
    private PostRepository postRepository;
    private ReactRespository reactRespository;
    private TagRepository tagRepository;

    @Autowired
    public DataLoader(ProfileRepository profileRepository, PostRepository postRepository, ReactRespository reactRespository, TagRepository tagRepository) {
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
        this.reactRespository = reactRespository;
        this.tagRepository = tagRepository;
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

        Profile profile3 = new Profile();
        profile3.setFname("Grace");
        profile3.setLname("Hopper");
        profile3.setEmail("grace@zapverse.dev");
        profile3.setPassword("compiler456");
        profile3 = profileRepository.save(profile3);

        Profile profile4 = new Profile();
        profile4.setFname("Dennis");
        profile4.setLname("Ritchie");
        profile4.setEmail("dennis@zapverse.dev");
        profile4.setPassword("unix789");
        profile4 = profileRepository.save(profile4);

        Post post1 = new Post();
        post1.setTopic("Computing Vision");
        post1.setContent("Envisioned the first algorithm way before computers existed.");
        post1.setProfile(profile1);

        Tag tag1 = new Tag();
        tag1.setTagType(TagType.PROGRAMMING);
        tag1.setPost(post1);
        tag1.setProfile(profile1);

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(tag1);
        post1 = postRepository.save(post1);
        tagRepository.save(tag1);

        post1.setTags(tags1);
        post1 = postRepository.save(post1);

        profile1.setPosts(List.of(post1));
        profileRepository.save(profile1);

        Post post2 = new Post();
        post2.setTopic("Programming Languages");
        post2.setContent("Developed the first compiler and influenced modern programming languages.");
        post2.setProfile(profile3);
        post2 = postRepository.save(post2);

        profile3.setPosts(List.of(post2));
        profileRepository.save(profile3);

        React react = new React();
        react.setPost(post1);
        react.setReaction(ReactionType.LIKE);
        react.setProfile(profile2);
        reactRespository.save(react);

        React react2 = new React();
        react2.setPost(post2);
        react2.setReaction(ReactionType.LOVE);
        react2.setProfile(profile4);
        reactRespository.save(react2);

        System.out.println("\nData saved to profile\n");
    }
}
