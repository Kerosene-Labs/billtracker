package com.kerosenelabs.billtracker.model.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleOAuthUserInfoResponse {
    private String sub;
    private String name;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    private String email;
    private String picture;

    @JsonProperty("email_verified")
    private boolean emailVerified;
}
