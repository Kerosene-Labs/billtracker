package com.kerosenelabs.billtracker.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.supabase.request.PostSignUpRequest;
import com.kerosenelabs.billtracker.model.supabase.response.PostSignUpResponse;

@Service("supabaseUserService")
public class SupabaseAuthService implements AuthService {
    private SupabaseClientService supabaseClientService;

    public SupabaseAuthService(SupabaseClientService supabaseClientService) {
        this.supabaseClientService = supabaseClientService;
    }

    @Override
    public void createUser(String email, String password) throws IOException, AuthException {
        PostSignUpResponse response = supabaseClientService.post("/auth/v1/signup",
                new PostSignUpRequest(email, password), PostSignUpResponse.class);
    }
}
