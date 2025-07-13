package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.dto.PostCreateDTO;
import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.dto.PostUpdateDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.Tag;
import com.zaplink.ZapVerse.model.TagType;
import com.zaplink.ZapVerse.repository.ReactRespository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTopic(post.getTopic());
        postDTO.setContent(post.getContent());
        postDTO.setTags(TagMapper.toDTO(post.getTags()));
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setModifiedAt(post.getModifiedAt());

        if (post.getProfile() != null) {
            postDTO.setFname(post.getProfile().getFname());
            postDTO.setLname(post.getProfile().getLname());
            postDTO.setAvatar(post.getProfile().getAvatar());

        }
        if (post.getTags() != null) {
            Set<String> tagStrings = post.getTags().stream().map(tag -> tag.getTagType().name()).collect(Collectors.toSet());
            postDTO.setTags(tagStrings);
        }

        postDTO.setLoveCount(post.getLoveCount() == null ? 0L : post.getLoveCount().longValue());

        return postDTO;
    }

    public static PostDTO toDTO(Post post, Profile currentUser, ReactRespository reactRepository) {
        PostDTO postDTO = toDTO(post); // call base method

        // Check if current user has reacted
        boolean userReacted = reactRepository.existsByPostAndProfile(post, currentUser);
        postDTO.setUserReacted(userReacted);

        return postDTO;
    }

    public static List<PostDTO> toDTO(List<Post> posts, Profile currentUser, ReactRespository reactRepository) {
        return posts.stream()
                .map(post -> toDTO(post, currentUser, reactRepository))
                .collect(Collectors.toList());
    }



    public static List<PostDTO> toDTO(List<Post> posts) {

        List<PostDTO> postDTOS = new ArrayList<>();

        for(Post post : posts) {
            PostDTO postDTO = toDTO(post);
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    public static Post fromCreateDTO(PostCreateDTO postCreateDTO, Profile profile) {
        Post post = new Post();
        post.setTopic(postCreateDTO.getTopic());
        post.setContent(postCreateDTO.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setModifiedAt(LocalDateTime.now());
        post.setProfile(profile);

        Set<Tag> tags = new HashSet<>();
        for(String tagStr : postCreateDTO.getTags()) {
            try {
                Tag tag = new Tag();
                tag.setTagType(TagType.valueOf(tagStr.toUpperCase()));
                tag.setPost(post);
                tag.setProfile(profile);
                tags.add(tag);
            }catch (Exception ex) {
                System.out.println("Error with tags");
            }
        }

        post.setTags(tags);
        return post;
    }

    public static Post fromUpdateDTO(Post post, PostUpdateDTO postUpdateDTO, Profile profile) {
        post.setTopic(postUpdateDTO.getTopic());
        post.setContent(postUpdateDTO.getContent());
        post.setCreatedAt(post.getCreatedAt());
        post.setModifiedAt(postUpdateDTO.getModifiedAt());
        post.setProfile(profile);

        Set<Tag> tags = new HashSet<>();
        for(String tagStr : postUpdateDTO.getTags()) {
            try {
                Tag tag = new Tag();
                tag.setTagType(TagType.valueOf(tagStr.toUpperCase()));
                tag.setPost(post);
                tag.setProfile(profile);
                tags.add(tag);
            }catch (Exception ex) {
                System.out.println("Error with tags");
            }
        }

        post.getTags().clear();
        post.getTags().addAll(tags);
        return post;
    }
}
