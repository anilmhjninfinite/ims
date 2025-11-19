package org.infinite.ims.user.repo;


import org.infinite.ims.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
