package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.dto.PostCreateDTO;
import com.zaplink.ZapVerse.dto.PostDTO;
import com.zaplink.ZapVerse.dto.PostUpdateDTO;
import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.TagType;
import com.zaplink.ZapVerse.service.PostService;
import com.zaplink.ZapVerse.utility.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false) String tag) {
        List<Post> posts;
        if((tag != null) && (!tag.isEmpty())) {
            TagType tagType;
            try {
                tagType = TagType.valueOf(tag.toUpperCase());
            }catch (Exception ex) {
                return ResponseEntity.ok(null);
            }
            posts = postService.getPostsByTag(tagType);
        }else {
            posts= postService.getAllPosts();
        }
        return ResponseEntity.ok(PostMapper.toDTO(posts));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        if(!(postService.existById(postId))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with id: " + postId + " not found!");
        }

        if(postService.deletePostById(postId)) {
            return ResponseEntity.ok("Post deleted successfully!");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete post!");
        }
    }

    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateDTO postCreateDTO) {
        Post post = postService.createPost(postCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.toDTO(post));
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable  int postId, @RequestBody PostUpdateDTO postUpdateDTO) {
        Post post = postService.updatePost(postId, postUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostMapper.toDTO(post));
    }
}
