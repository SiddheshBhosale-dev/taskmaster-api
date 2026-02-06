package com.TaskMaster.taskmaster_api.controller;

import com.TaskMaster.taskmaster_api.model.User;
import com.TaskMaster.taskmaster_api.service.UserService;

import jakarta.validation.Valid;
import com.TaskMaster.taskmaster_api.model.User;


import com.TaskMaster.taskmaster_api.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // User Registration Endpoint
    @PostMapping("/register")
    public String register(@RequestBody @Valid User user) {
        return userService.registerUser(user);  // Return a string message
    }

    // User Login Endpoint (returns JWT token)
 // In AuthenticationController.java
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User user) {
        // Authenticate user
        User authenticatedUser = userService.authenticateUser(user);

        if (authenticatedUser != null) {
            // Return JWT token if authenticated
            return jwtTokenUtil.generateToken(authenticatedUser.getUsername());
        } else {
            // Return error if authentication failed
            throw new RuntimeException("Authentication failed! Invalid username or password.");
        }
    }


}
