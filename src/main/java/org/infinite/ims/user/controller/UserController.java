package org.infinite.ims.user.controller;

import org.infinite.ims.user.model.User;
import org.infinite.ims.user.service.UserService;
import org.infinite.ims.util.ApiResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value  ="/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseBuilder()
                .status(HttpStatus.OK)
                .message("Users fetched successfully")
                .data(users)
                .build()
        );
    }
}
