package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.service.PostService;
import com.zaplink.ZapVerse.utility.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(PostMapper.toDTO(post));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> allPosts= postService.getAllPosts();
        return ResponseEntity.ok(PostMapper.toDTO(allPosts));
    }




}
