package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.model.*;
import com.zaplink.ZapVerse.repository.ReactRespository;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DataLoader {

    private ProfileRepository profileRepository;
    private PostRepository postRepository;
    private ReactRespository reactRespository;
    private TagRepository tagRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(ProfileRepository profileRepository, PostRepository postRepository, ReactRespository reactRespository, TagRepository tagRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
        this.reactRespository = reactRespository;
        this.tagRepository = tagRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {

        Profile profile1 = new Profile();
        profile1.setFname("Ada");
        profile1.setLname("Lovelace");
        profile1.setEmail("ada@zapverse.dev");
        // profile1.setPassword("analytical123");
        profile1.setAvatar("ava.png");
        profile1.setPassword(passwordEncoder.encode("analytical123"));
        profile1 = profileRepository.save(profile1);

        Profile profile2 = new Profile();
        profile2.setFname("Alan");
        profile2.setLname("Turing");
        profile2.setEmail("alan@zapverse.dev");
        // profile2.setPassword("enigma123");
        profile2.setAvatar("jonas.png");
        profile2.setPassword(passwordEncoder.encode("enigma123"));
        profile2 = profileRepository.save(profile2);

        Profile profile3 = new Profile();
        profile3.setFname("Grace");
        profile3.setLname("Hopper");
        profile3.setEmail("grace@zapverse.dev");
        // profile3.setPassword("compiler456");
        profile3.setAvatar("liam.png");
        profile3.setPassword(passwordEncoder.encode("compiler456"));
        profile3 = profileRepository.save(profile3);

        Profile profile4 = new Profile();
        profile4.setFname("Dennis");
        profile4.setLname("Ritchie");
        profile4.setEmail("dennis@zapverse.dev");
        // profile4.setPassword("unix789");
        profile4.setAvatar("milo.png");
        profile4.setPassword(passwordEncoder.encode("unix789"));
        profile4 = profileRepository.save(profile4);

        Post post1 = new Post();
        post1.setTopic("Computing Vision");
        post1.setContent("Envisioned the first algorithm way before computers existed.");
        post1.setCreatedAt(LocalDate.of(2025, 2, 1));
        post1.setModifiedAt(LocalDate.of(2025, 6, 2));
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
        post2.setCreatedAt(LocalDate.of(2024, 4, 12));
        post2.setModifiedAt(LocalDate.of(2024, 8, 20));
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

        // Post 3 - Alan with AI tag
        Post post3 = new Post();
        post3.setTopic("Artificial Intelligence");
        post3.setContent("Pioneered ideas that became the foundation of AI.");
        post3.setCreatedAt(LocalDate.of(2026, 1, 9));
        post3.setModifiedAt(LocalDate.of(2026, 2, 2));
        post3.setProfile(profile2);
        post3 = postRepository.save(post3);

        Tag tag3 = new Tag();
        tag3.setTagType(TagType.AI);
        tag3.setPost(post3);
        tag3.setProfile(profile2);
        tagRepository.save(tag3);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(tag3);
        post3.setTags(tags3);
        postRepository.save(post3);

// Post 4 - Grace with SECURITY tag
        Post post4 = new Post();
        post4.setTopic("Compiler Security");
        post4.setContent("Early thoughts on securing compilers and runtime.");
        post4.setCreatedAt(LocalDate.of(2023, 12, 1));
        post4.setModifiedAt(LocalDate.of(2023, 12, 1));
        post4.setProfile(profile3);
        post4 = postRepository.save(post4);

        Tag tag4 = new Tag();
        tag4.setTagType(TagType.PROGRAMMING);
        tag4.setPost(post4);
        tag4.setProfile(profile3);
        tagRepository.save(tag4);

        Set<Tag> tags4 = new HashSet<>();
        tags4.add(tag4);
        post4.setTags(tags4);
        postRepository.save(post4);

// Post 5 - Dennis with SYSTEMS tag
        Post post5 = new Post();
        post5.setTopic("Operating Systems");
        post5.setContent("Helped create Unix, influencing all modern OSes.");
        post5.setCreatedAt(LocalDate.of(2025, 7, 1));
        post5.setModifiedAt(LocalDate.of(2025, 12, 31));
        post5.setProfile(profile4);
        post5 = postRepository.save(post5);

        Tag tag5 = new Tag();
        tag5.setTagType(TagType.SCIENCE);
        tag5.setPost(post5);
        tag5.setProfile(profile4);
        tagRepository.save(tag5);

        Set<Tag> tags5 = new HashSet<>();
        tags5.add(tag5);
        post5.setTags(tags5);
        postRepository.save(post5);

        System.out.println("\nData saved to profile\n");
    }
}
