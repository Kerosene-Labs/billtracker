package com.kerosenelabs.billtracker.service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleOAuth2ProviderService implements OAuth2ProviderService {
    public final String tokenEndpoint;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    public GoogleOAuth2ProviderService(
            @Value("${billtracker.oauth2.providers.google.tokenEndpoint}") String tokenEndpoint,
            @Value("${billtracker.oauth2.providers.google.clientId}") String clientId,
            @Value("${billtracker.oauth2.providers.google.clientSecret}") String clientSecret,
            @Value("${billtracker.oauth2.providers.google.redirectUri}") String redirectUri) {
        this.tokenEndpoint = tokenEndpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    private void makeTokenRequest() {

    }

    @Override
    public UserEntity handleCode(String code) {
        OkHttpClient client = new OkHttpClient();
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
            System.out.println(response.body().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
