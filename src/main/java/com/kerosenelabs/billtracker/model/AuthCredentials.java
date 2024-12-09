package com.kerosenelabs.billtracker.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

public class AuthCredentials {
    private String accessToken;
    private String refreshToken;

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static AuthCredentials fromJson(String json) throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(json, AuthCredentials.class);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    private AuthCredentials(Builder builder) {
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
    }

    public static class Builder {
        private HttpServletRequest httpServletRequest;
        private String accessToken;
        private String refreshToken;

        public Builder(HttpServletRequest httpServletRequest) {
            this.httpServletRequest = httpServletRequest;
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthCredentials build() {
            return new AuthCredentials(this);
        }
    }
}
