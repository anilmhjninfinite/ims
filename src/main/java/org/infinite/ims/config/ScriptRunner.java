package org.infinite.ims.config;

import org.infinite.ims.user.model.Role;
import org.infinite.ims.user.model.User;
import org.infinite.ims.user.repo.RoleRepository;
import org.infinite.ims.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ScriptRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Not a good approach
    //For development purpose only
    //ddl auto != none
    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = Arrays.asList(new Role("ADMIN"), new Role("USER"));
        for(Role role : roles){
            Role existedRole = roleRepository.findByRoleName(role.getRoleName());
            if(existedRole == null){
                roleRepository.save(role);
            }
        }

        User user = new User();
        user.setUsername("admin");
        user.setPassword(bCryptPasswordEncoder.encode("adminadmin"));
        user.setRoles(roles);
        user.setEmail("admin@gmail.com");
        user.setCreatedDate(LocalDateTime.now());
        user.setContact("1234567890");
        user.setAccountEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setLastLoginDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setUpdatedDate(LocalDateTime.now());

        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser == null){
            userRepository.save(user);
        }


    }
}
