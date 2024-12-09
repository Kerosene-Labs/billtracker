package com.kerosenelabs.billtracker.model;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentials {
    private String accessToken;
    private String refreshToken;
}
