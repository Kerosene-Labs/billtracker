package com.kerosenelabs.billtracker.model.external.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleOAuthUserInfoResponse(
    val sub: String,
    val name: String,
    @JsonProperty("given_name") val givenName: String,
    @JsonProperty("family_name") val familyName: String,
    val email: String,
    val picture: String,
    @JsonProperty("email_verified") val emailVerified: Boolean
)
