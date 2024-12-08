package com.kerosenelabs.billtracker.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.supabase.auth.request.CreateSignUpRequest;
import com.kerosenelabs.billtracker.model.supabase.auth.response.CreateSignUpResponse;

@Service("supabaseUserService")
public class SupabaseAuthService implements AuthService {
    private SupabaseClientService supabaseClientService;

    public SupabaseAuthService(SupabaseClientService supabaseClientService) {
        this.supabaseClientService = supabaseClientService;
    }

    @Override
    public void createUser(String email, String password) throws IOException, AuthException {
        CreateSignUpResponse response = supabaseClientService.post("/auth/v1/signup",
                new CreateSignUpRequest(email, password), CreateSignUpResponse.class);
    }

    @Override
    public String getGrant(String email, String password) throws IOException, AuthException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGrant'");
    }
}
