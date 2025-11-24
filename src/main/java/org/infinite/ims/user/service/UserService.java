package org.infinite.ims.user.service;

import org.infinite.ims.jwt.service.JwtService;
import org.infinite.ims.user.model.Role;
import org.infinite.ims.user.model.User;
import org.infinite.ims.user.repo.RoleRepository;
import org.infinite.ims.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerUser(User user) {
        List<Role> roles = new ArrayList<>();
        for(Role role : user.getRoles()) {
            Role existingRole = roleRepo.findByRoleName(role.getRoleName());

            if(existingRole == null) {
                throw new RuntimeException("Role not found: " + role.getRoleName());
            }
            roles.add(existingRole);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        userRepo.save(user);
        return user;
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }


    public Map<String, Object> validateUser(User user) {
        Map<String, Object> map = new HashMap<>();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        String token = jwtService.generateToken(user.getUsername());
        Date date = jwtService.extractExpiration(token);
        map.put("token", token);
        map.put("expiration", date);
        return map;
    }
}
