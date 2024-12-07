package com.kerosenelabs.billtracker.service;

import org.springframework.stereotype.Service;

@Service("supabaseUserService")
public class SupabaseAuthService implements AuthService {
    private SupabaseClientService supabaseClientService;

    public SupabaseAuthService(SupabaseClientService supabaseClientService) {
        this.supabaseClientService = supabaseClientService;
    }

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        
    }
}
