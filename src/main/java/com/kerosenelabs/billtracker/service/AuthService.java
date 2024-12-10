package com.kerosenelabs.billtracker.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.AuthCredentials;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    /**
     * Create a new user
     * 
     * @param email
     * @param password
     * @throws IOException
     * @throws AuthException
     */
    public void createUser(String email, String password) throws IOException, AuthException {
        
    }

    /**
     * Check if this sessions credentials are expired.
     */
    public boolean isCredentialsExpired(AuthCredentials authCredentials);

    /**
     * Get credentials from the provider.
     * 
     * @param email
     * @param password
     * @return
     * @throws IOException
     * @throws AuthException
     */
    public AuthCredentials generateCredentials(String email, String password) throws IOException, AuthException;

    /**
     * Persist auth credentials to the session.
     * 
     * @param httpSession     The session from the controller
     * @param authCredentials The credentials to persist
     * @throws AuthException
     */
    default public void persistCredentialsToSession(HttpSession httpSession, AuthCredentials authCredentials)
            throws AuthException {
        try {
            httpSession.setAttribute("authCredentials", authCredentials.toJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AuthException("An error occurred while writing JSON of AuthCredentials to session");
        }
    }

    /**
     * Refresh the given credentials. This modifies the passed in instance. Please
     * note that your implementation should probably call
     * {@link this#persistCredentialsToSession(HttpSession, AuthCredentials)}
     * to persist your credential changes.
     * 
     * @param authCredentials
     */
    public void refreshCredentials(AuthCredentials authCredentials);

    /**
     * Helper to read the session and extract the {@code authCredentials} attribute.
     * 
     * @param httpSession
     * @return
     * @throws AuthException
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public AuthCredentials getCredentialsFromSession(HttpSession httpSession)
            throws AuthException, JsonMappingException, JsonProcessingException;
}
