package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.request.HandleTokenRequest;
import com.kerosenelabs.billtracker.service.GoogleOAuth2ProviderService;
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

    public OAuth2Controller(GoogleOAuth2ProviderService googleOAuth2ProviderService) {
        this.googleOAuth2ProviderService = googleOAuth2ProviderService;
    }

    @PostMapping("/oauth/google")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleToken(@RequestBody HandleTokenRequest request) throws IOException, AuthException {
        googleOAuth2ProviderService.handleCode(request.getCode());
    }
}
