package com.kerosenelabs.billtracker.model.supabase.auth.response;

import com.kerosenelabs.billtracker.model.supabase.UserSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Integer expiresIn;
    private Integer expiresAt;
    private UserSchema user;
}
