package com.gagara.final_project.payload;

import java.util.Collection;
import java.util.Set;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Collection<?> roles;

    public JwtAuthenticationResponse(String accessToken, Collection<?> roles) {
        this.accessToken = accessToken;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Collection<?> getRoles() {
        return roles;
    }

    public void setRoles(Collection<?> roles) {
        this.roles = roles;
    }
}
