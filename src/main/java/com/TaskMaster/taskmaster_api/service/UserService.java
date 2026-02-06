package com.TaskMaster.taskmaster_api.service;

import com.TaskMaster.taskmaster_api.model.User;
import com.TaskMaster.taskmaster_api.repository.UserRepository;
import com.TaskMaster.taskmaster_api.security.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.TaskMaster.taskmaster_api.model.User;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Handle user login
    public String loginUser(User user) {
        // Implement authentication logic (e.g., check username & password)
        // If successful, generate a JWT token and return it
        String token = jwtTokenUtil.generateToken(user.getUsername());  // Use username, not user object
        return token;
    }

    public String registerUser(User user) {
        // Ensure that the password is hashed before saving to the database
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword); // Set the hashed password
        userRepository.save(user); // Save the user with the hashed password
        return "User registered successfully"; // Return success message
    }





    public User authenticateUser(User user) {
        // Retrieve user from the database
        User storedUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        // Check if the user exists and if the passwords match
        if (storedUser != null && passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            return storedUser; // If passwords match, return the user
        }

        // Return null if authentication fails
        return null;
    }





}
