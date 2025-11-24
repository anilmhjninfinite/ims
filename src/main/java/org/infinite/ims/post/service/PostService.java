package org.infinite.ims.post.service;

import org.infinite.ims.post.model.Post;
import org.infinite.ims.post.model.Status;
import org.infinite.ims.post.repo.PostRepository;
import org.infinite.ims.user.model.User;
import org.infinite.ims.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void createPost(Post post) {
        Long userId = post.getUser().getId();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        post.setViewsCount(0);
        post.setLikesCount(0);
        post.setFeatured(false);
        post.setPinned(false);
        post.setUser(user);
        post.setStatus(Status.OPEN);
        post.setCreateDate(LocalDateTime.now());
        post.setUpdatedDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findByPostId(Long id) {
        return postRepository.findById(id);
    }

    public void updatePostStatus(Long id, Status status) {
        Post post = findByPostId(id).orElseThrow(() ->
                new RuntimeException("Post not found")
        );

        post.setUpdatedDate(LocalDateTime.now());
        post.setStatus(status);
        postRepository.save(post);

    }

    public void updatePostLikes(Long id) {
        Post post = findByPostId(id).orElseThrow(() ->
                new RuntimeException("Post not found")
        );
        post.setUpdatedDate(LocalDateTime.now());
        post.setLikesCount(post.getLikesCount() + 1);
        postRepository.save(post);
    }

    public void updatePostViews(Long id) {
        Post post = findByPostId(id).orElseThrow(() ->
                new RuntimeException("Post not found")
        );
        post.setUpdatedDate(LocalDateTime.now());
        post.setViewsCount(post.getViewsCount() + 1);
        postRepository.save(post);
    }

    public List<Post> findPostsByStatus(Status status) {
        return postRepository.findByStatus(status);
    }
}
