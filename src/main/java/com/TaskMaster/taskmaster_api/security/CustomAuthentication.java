package com.TaskMaster.taskmaster_api.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CustomAuthentication extends AbstractAuthenticationToken {

    private final String username;

    public CustomAuthentication(String username) {
        super(null); // No authorities, we'll skip that part
        this.username = username;
        setAuthenticated(true); // Mark the token as authenticated
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials are needed, we are only using the username
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
