package org.infinite.ims.post.controller;

import org.infinite.ims.post.model.Comment;
import org.infinite.ims.post.service.CommentService;
import org.infinite.ims.util.ApiResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "{postId}")
    public ResponseEntity<Map<String, Object>> post(
            @RequestBody Comment comment,
            @PathVariable("postId") Long postId
    ) {
        commentService.saveComment(comment,postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.CREATED)
                        .message("Comments added successfully")
                        .build()
        );
    }

}
