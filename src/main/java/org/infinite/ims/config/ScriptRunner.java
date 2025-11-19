package org.infinite.ims.config;

import org.infinite.ims.user.model.Role;
import org.infinite.ims.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ScriptRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    //Not a good approach
    //For development purpose only
    //ddl auto != none
    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = Arrays.asList(new Role("ADMIN"), new Role("USER"));
        for(Role role : roles){
            Role existedRole = roleService.getRoleByName(role.getRoleName());
            if(existedRole == null){
                roleService.saveRole(role);
            }
        }
    }
}
