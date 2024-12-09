package com.kerosenelabs.billtracker.model;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentials {
    private String accessToken;
    private String refreshToken;
    private UUID userId;
    private Instant expiresAt;

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static AuthCredentials fromJson(String json) throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(json, AuthCredentials.class);
    }
}
