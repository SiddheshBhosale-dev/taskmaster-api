package com.TaskMaster.taskmaster_api.controller;

import com.TaskMaster.taskmaster_api.model.User;
import com.TaskMaster.taskmaster_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    


    // Login a user
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.loginUser(user);
    }
}
