package com.kerosenelabs.billtracker.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandleOAuth2TokenResponse {
    private String jwt;
}
