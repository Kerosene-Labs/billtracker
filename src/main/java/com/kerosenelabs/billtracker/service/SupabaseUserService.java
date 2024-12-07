package com.kerosenelabs.billtracker.service;

import org.springframework.stereotype.Service;

@Service("supabaseUserService")
public class SupabaseUserService implements UserService {
    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
    }
}
