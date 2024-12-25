package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.request.HandleTokenRequest;
import com.kerosenelabs.billtracker.model.response.HandleOAuth2TokenResponse;
import com.kerosenelabs.billtracker.service.GoogleOAuth2ProviderService;
import com.kerosenelabs.billtracker.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Tag(name = "OAuth2")
public class OAuth2Controller {
    private final GoogleOAuth2ProviderService googleOAuth2ProviderService;
    private final UserService userService;

    public OAuth2Controller(GoogleOAuth2ProviderService googleOAuth2ProviderService, UserService userService) {
        this.googleOAuth2ProviderService = googleOAuth2ProviderService;
        this.userService = userService;
    }

    @PostMapping("/oauth/google")
    @ResponseStatus(HttpStatus.OK)
    public HandleOAuth2TokenResponse handleToken(@RequestBody HandleTokenRequest request) throws IOException, AuthException {
        UserEntity user = googleOAuth2ProviderService.handleCode(request.getCode());
        String jwt = userService.establishJwt(user);
        return new HandleOAuth2TokenResponse(jwt);
    }
}
