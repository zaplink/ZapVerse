package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.model.Post;

public class PostMapper {

    public static PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTopic(post.getTopic());
            postDTO.setContent(post.getContent());
            return postDTO;
    }
}
