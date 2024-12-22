package com.kerosenelabs.billtracker.service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface OAuth2ProviderService {
    /**
     * Call the provider's {@code token} endpoint and either create or find the user in our database.
     * @param code The code from the user's browser
     * @return The either existing or newly created user
     */
    public UserEntity handleCode(String code) throws IOException, AuthException;
}
