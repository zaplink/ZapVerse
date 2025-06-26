package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable int postId) {
        return postService.getPostById(postId);
    }
}
