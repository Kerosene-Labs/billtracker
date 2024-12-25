package com.kerosenelabs.billtracker.controller

import com.kerosenelabs.billtracker.exception.AuthException
import com.kerosenelabs.billtracker.model.request.HandleTokenRequest
import com.kerosenelabs.billtracker.model.response.HandleOAuth2TokenResponse
import com.kerosenelabs.billtracker.service.GoogleOAuth2ProviderService
import com.kerosenelabs.billtracker.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@RestController
@Tag(name = "OAuth2")
class OAuth2Controller(
    private val googleOAuth2ProviderService: GoogleOAuth2ProviderService,
    private val userService: UserService
) {
    @PostMapping("/oauth/google")
    @ResponseStatus(HttpStatus.OK)
    @Throws(
        IOException::class,
        AuthException::class
    )
    fun handleToken(@RequestBody request: HandleTokenRequest): HandleOAuth2TokenResponse {
        val user = googleOAuth2ProviderService.handleCode(request.code)
        val jwt = userService.establishJwt(user)
        return HandleOAuth2TokenResponse(jwt)
    }
}
