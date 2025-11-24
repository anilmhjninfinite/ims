package org.infinite.ims.post.controller;

import org.infinite.ims.post.model.Post;
import org.infinite.ims.post.model.Status;
import org.infinite.ims.post.service.PostService;
import org.infinite.ims.util.ApiResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> post(@RequestBody Post post) {
        postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.CREATED)
                        .message("Post created successfully")
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> get(){
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Posts fetched successfully")
                        .data(posts)
                        .build()
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getByPostId(@PathVariable("id") Long id){
        Optional<Post> post = postService.findByPostId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Posts fetched successfully")
                        .data(post)
                        .build()
        );
    }

    @PutMapping(value = "/{id}/status")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable ("id") Long id,
            @RequestParam ("status") Status status
            ){
        postService.updatePostStatus(id,status);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Posts updated successfully")
                        .build()
        );
    }

    @PutMapping(value = "/{id}/likes")
    public ResponseEntity<Map<String, Object>> updateLikes(
            @RequestParam ("id") Long id
            ){
        postService.updatePostLikes(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Posts updated successfully")
                        .build()
        );
    }

    @PutMapping(value = "/{id}/views")
    public ResponseEntity<Map<String, Object>> updateViews(
            @RequestParam ("id") Long id
            ){
        postService.updatePostViews(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Posts updated successfully")
                        .build()
        );
    }

    @GetMapping(params = "status")
    public ResponseEntity<Map<String, Object>> getByStatus(@RequestParam("status") Status status){
        List<Post> post = postService.findPostsByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Posts fetched successfully ::"+status)
                        .data(post)
                        .build()
        );
    }
}
