package com.TaskMaster.taskmaster_api.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();  // Utility class for handling token generation and validation

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response, 
                                     FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        // Check if token exists and starts with "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remove "Bearer " prefix from the token

            try {
                // Extract username from token
                String username = jwtTokenUtil.getUsernameFromToken(token);

                // Validate token and set authentication if valid
                if (username != null && jwtTokenUtil.validateToken(token, username)) {
                    Authentication authentication = new CustomAuthentication(username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                // Token has expired, set response to unauthorized
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has expired.");
                return;
            }
        }

        // Proceed with the filter chain (let the request continue)
        filterChain.doFilter(request, response);
    }
}
