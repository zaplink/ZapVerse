package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostMapper {

    public static PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTopic(post.getTopic());
            postDTO.setContent(post.getContent());
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
}
