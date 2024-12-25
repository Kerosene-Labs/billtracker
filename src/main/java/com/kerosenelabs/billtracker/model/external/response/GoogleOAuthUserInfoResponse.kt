package com.kerosenelabs.billtracker.model.external.response

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class GoogleOAuthUserInfoResponse {
    private var sub: String? = null
    private var name: String? = null

    @JsonProperty("given_name")
    private var givenName: String? = null

    @JsonProperty("family_name")
    private var familyName: String? = null

    private var email: String? = null
    private var picture: String? = null

    @JsonProperty("email_verified")
    private var emailVerified = false
}
