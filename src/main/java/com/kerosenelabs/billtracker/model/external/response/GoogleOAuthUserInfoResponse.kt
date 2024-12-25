package com.kerosenelabs.billtracker.model.external.response

data class GoogleOAuthUserInfoResponse(
    val sub: String,
    val name: String,
    val givenName: String,
    val familyName: String,
    val email: String,
    val picture: String,
    val emailVerified: Boolean
)
