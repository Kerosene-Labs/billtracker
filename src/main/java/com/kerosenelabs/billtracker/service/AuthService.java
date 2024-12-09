package com.kerosenelabs.billtracker.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.AuthCredentials;

import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
public interface AuthService {
    public void createUser(String email, String password) throws IOException, AuthException;

    public AuthCredentials getCredentials(String email, String password) throws IOException, AuthException;

    /**
     * Establish a new session with the given credentials. These credentials will be
     * persisted, and the browser will be given a new session.
     * 
     * @param httpSession     The session from the controller
     * @param authCredentials The credentials to base this session on
     */
    default public void establishSession(HttpSession httpSession, AuthCredentials authCredentials) {
        httpSession.setAttribute("authCredentials", authCredentials);
        System.out.println("Persisted authCredentials");
    }
}
