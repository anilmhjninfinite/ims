package org.infinite.ims.post.repo;

import org.infinite.ims.post.model.Post;
import org.infinite.ims.post.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(Status status);
}
