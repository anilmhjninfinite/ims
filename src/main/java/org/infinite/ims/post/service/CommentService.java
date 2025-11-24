package org.infinite.ims.post.service;

import org.infinite.ims.post.model.Comment;
import org.infinite.ims.post.model.Post;
import org.infinite.ims.post.model.Status;
import org.infinite.ims.post.repo.CommentRepository;
import org.infinite.ims.post.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    public void saveComment(Comment comment, Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            throw new RuntimeException("Post not found");
        }
        if(post.getStatus() != Status.PUBLISHED){
            throw new RuntimeException("Error adding post to post status :"+post.getStatus());
        }
        comment.setPost(post);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUpdatedDate(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
