package com.kerosenelabs.billtracker.model.external.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleOAuthTokenResponse(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val expiresIn: Int,
    @JsonProperty("scope") val scope: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("id_token") val idToken: String,
)