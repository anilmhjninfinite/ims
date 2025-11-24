package org.infinite.ims.user.controller;

import org.infinite.ims.user.model.Role;
import org.infinite.ims.user.service.RoleService;
import org.infinite.ims.util.ApiResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService repoService;

    @PostMapping(value="/roles")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Role role) {
        Role response = repoService.saveRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.CREATED)
                        .message("Role added successfully")
                        .data(response)
                        .build()
        );

    }

    @GetMapping(value="/roles")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Role> roles = repoService.getAllRoles();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseBuilder()
                        .status(HttpStatus.OK)
                        .message("Roles fetched successfully")
                        .data(roles)
                        .build()

        );
    }
}
