package com.kerosenelabs.billtracker.model.response

import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
class HandleOAuth2TokenResponse {
    private val jwt: String? = null
}
