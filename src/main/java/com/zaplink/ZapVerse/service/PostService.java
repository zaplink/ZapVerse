package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.TagType;
import com.zaplink.ZapVerse.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByTag(TagType tagType) {
        return postRepository.findDistinctByTags_TagType(tagType);
    }

    public boolean deletePostById(int postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        }catch (Exception ex) {
            return false;
        }

    }

    public boolean existById(int postId) {
        return postRepository.existsById(postId);
    }
}
