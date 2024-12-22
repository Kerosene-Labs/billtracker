package com.kerosenelabs.billtracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.OAuth2Provider;
import com.kerosenelabs.billtracker.model.external.response.GoogleOAuthUserInfoResponse;
import com.kerosenelabs.billtracker.model.external.response.GoogleOAuthTokenResponse;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class GoogleOAuth2ProviderService implements OAuth2ProviderService {
    public final String tokenEndpoint;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String userInfoEndpoint;
    private final UserService userService;

    public GoogleOAuth2ProviderService(
            @Value("${billtracker.oauth2.providers.google.tokenEndpoint}") String tokenEndpoint,
            @Value("${billtracker.oauth2.providers.google.clientId}") String clientId,
            @Value("${billtracker.oauth2.providers.google.clientSecret}") String clientSecret,
            @Value("${billtracker.oauth2.providers.google.redirectUri}") String redirectUri,
            @Value("${billtracker.oauth2.providers.google.userInfoEndpoint}") String userInfoEndpoint,
            UserService userService) {
        this.tokenEndpoint = tokenEndpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.userInfoEndpoint = userInfoEndpoint;
        this.userService = userService;
    }

    private GoogleOAuthTokenResponse getTokenResponse(String code) throws IOException, AuthException {
        OkHttpClient client = new OkHttpClient();
        System.out.println("redirectUri is: " + redirectUri);
        RequestBody body = new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("code", code)
                .add("grant_type", "authorization_code")
                .add("redirect_uri", redirectUri)
                .build();
        Request request = new Request.Builder()
                .url(tokenEndpoint)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String bodyContent = response.body().string();
            if (!response.isSuccessful()) {
                throw new AuthException("Got error response from provider: " + bodyContent);
            }
            ObjectMapper mapper = new ObjectMapper();
            GoogleOAuthTokenResponse tokenResponse = mapper.readValue(bodyContent, GoogleOAuthTokenResponse.class);
            return tokenResponse;
        }
    }

    private GoogleOAuthUserInfoResponse getUserInfoFromProvider(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(userInfoEndpoint)
                .addHeader("Authorization", String.format("Bearer %s", accessToken))
                .build();
        try (Response response = client.newCall(request).execute()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().string(), GoogleOAuthUserInfoResponse.class);
        }
    }

    @Override
    public UserEntity handleCode(String code) throws IOException, AuthException {
        // get our token response from Google
        GoogleOAuthTokenResponse oAuthTokenResponse = getTokenResponse(code);

        // get our email from the provider
        GoogleOAuthUserInfoResponse userInfo = getUserInfoFromProvider(oAuthTokenResponse.getAccessToken());

        // fetch our user or create them
        Optional<UserEntity> userEntity = Optional.empty();
        try {
            userEntity = Optional.of(userService.getUserBySubAndProvider(userInfo.getSub(), OAuth2Provider.GOOGLE));
        } catch (AuthException e) {
            userEntity = Optional.of(userService.createUser(userInfo.getEmail(), userInfo.getSub(), OAuth2Provider.GOOGLE, userInfo.getGivenName(), userInfo.getFamilyName()));
        }
        return userEntity.orElseThrow(() -> new AuthException("Undefined behavior, User was not found and was not created."));
    }
}
