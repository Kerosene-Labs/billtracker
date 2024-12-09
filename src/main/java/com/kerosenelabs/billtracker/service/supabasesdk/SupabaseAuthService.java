package com.kerosenelabs.billtracker.service.supabasesdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.AuthCredentials;
import com.kerosenelabs.billtracker.model.supabase.auth.request.CreateSignUpRequest;
import com.kerosenelabs.billtracker.model.supabase.auth.request.CreateTokenRequest;
import com.kerosenelabs.billtracker.model.supabase.auth.response.CreateSignUpResponse;
import com.kerosenelabs.billtracker.model.supabase.auth.response.CreateTokenResponse;
import com.kerosenelabs.billtracker.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service("supabaseUserService")
public class SupabaseAuthService implements AuthService {
    private SupabaseClientService supabaseClientService;

    public SupabaseAuthService(SupabaseClientService supabaseClientService) {
        this.supabaseClientService = supabaseClientService;
    }

    @Override
    public void createUser(String email, String password) throws IOException, AuthException {
        CreateSignUpResponse response = supabaseClientService.post("/auth/v1/signup",
                Optional.empty(),
                new CreateSignUpRequest(email, password),
                CreateSignUpResponse.class);
    }

    @Override
    public AuthCredentials generateCredentials(String email, String password)
            throws IOException, AuthException {
        CreateTokenResponse response = supabaseClientService.post("/auth/v1/token",
                Optional.of(new HashMap<>() {
                    {
                        put("grant_type", "password");
                    }
                }),
                new CreateTokenRequest(email, password),
                CreateTokenResponse.class);
        return new AuthCredentials(response.getAccessToken(), response.getRefreshToken(), response.getUser().getId());
    }

    @Override
    public void refreshCredentials(AuthCredentials authCredentials) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshCredentials'");
    }

    @Override
    public AuthCredentials getCredentialsFromSession(HttpSession httpSession)
            throws AuthException, JsonMappingException, JsonProcessingException {
        // Retrieve and return the session attribute
        Object attribute = httpSession.getAttribute("authCredentials");
        if (attribute == null) {
            throw new AuthException("Session cookie not set");
        }
        return AuthCredentials.fromJson((String) attribute);
    }

    @Override
    public boolean isSessionResumable(HttpSession httpSession) throws IOException, AuthException {
        return false;
    }
}
