package com.kerosenelabs.billtracker.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.exception.AuthException;

@Service
public interface AuthService {
    public void createUser(String email, String password) throws IOException, AuthException;
}
