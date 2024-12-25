package com.kerosenelabs.billtracker.model.external.response

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class GoogleOAuthTokenResponse {
    @JsonProperty("access_token")
    private var accessToken: String? = null

    @JsonProperty("expires_in")
    private var expiresIn: Int? = null

    @JsonProperty("scope")
    private var scope: String? = null

    @JsonProperty("token_type")
    private var tokenType: String? = null

    @JsonProperty("id_token")
    private var idToken: String? = null
}
