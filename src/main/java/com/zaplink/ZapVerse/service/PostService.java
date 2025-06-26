package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
