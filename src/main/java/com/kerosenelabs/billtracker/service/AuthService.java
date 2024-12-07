package com.kerosenelabs.billtracker.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public void createUser(String firstName, String lastName, String email, String password);
}
