package com.TaskMaster.taskmaster_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()  // Updated to the new method
            .requestMatchers("/api/authenticate", "/api/register").permitAll()  // Public access to registration and login
            .anyRequest().authenticated()  // Require authentication for other endpoints
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);  // Custom filter for JWT authentication

        return http.build();  // Return the configured filter chain
    }
}
