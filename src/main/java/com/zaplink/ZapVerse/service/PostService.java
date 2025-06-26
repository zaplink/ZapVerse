package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.dto.PostCreateDTO;
import com.zaplink.ZapVerse.dto.PostUpdateDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.TagType;
import com.zaplink.ZapVerse.repository.PostRepository;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import com.zaplink.ZapVerse.utility.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;
    ProfileRepository profileRepository;

    public PostService(PostRepository postRepository, ProfileRepository profileRepository) {
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
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

    public Post createPost(PostCreateDTO postCreateDTO) {
        Profile profile = profileRepository
                .findById(postCreateDTO.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found!"));

        Post post = PostMapper.fromCreateDTO(postCreateDTO, profile);
        return postRepository.save(post);
    }

    public Post updatePost(int postId, PostUpdateDTO postUpdateDTO) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found!"));

        Profile profile = profileRepository
                .findById(postUpdateDTO.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found!"));

        Post newPost = PostMapper.fromUpdateDTO(post, postUpdateDTO, profile);

        newPost.setId(postId);

        return postRepository.save(newPost);

    }
}
