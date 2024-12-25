package com.kerosenelabs.billtracker.model.external.response

class GoogleOAuthUserInfoResponse(
    val sub: String,
    val name: String,
    val givenName: String,
    val familyName: String,
    val email: String,
    val picture: String,
    val emailVeriied: Boolean
)
