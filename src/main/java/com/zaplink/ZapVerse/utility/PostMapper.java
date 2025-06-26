package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.dto.PostCreateDTO;
import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.Tag;
import com.zaplink.ZapVerse.model.TagType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostMapper {

    public static PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTopic(post.getTopic());
            postDTO.setContent(post.getContent());
            postDTO.setTags(TagMapper.toDTO(post.getTags()));
            postDTO.setCreatedAt(post.getCreatedAt());
            postDTO.setModifiedAt(post.getModifiedAt());
            return postDTO;
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
        post.setCreatedAt(postCreateDTO.getCreatedAt());
        post.setModifiedAt(postCreateDTO.getCreatedAt());
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
}
