package com.kerosenelabs.billtracker.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandleOAuthTokenResponse {
    private String accessToken;
}
