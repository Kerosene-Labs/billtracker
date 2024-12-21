package com.kerosenelabs.billtracker.model.external.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleOAuthTokenResponse {
    private String accessToken;
    private Integer expiresIn;
    private String scope;
    private String tokenType;
    private String idToken;
}
