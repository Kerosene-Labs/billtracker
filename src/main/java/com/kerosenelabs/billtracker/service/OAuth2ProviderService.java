package com.kerosenelabs.billtracker.service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface OAuth2ProviderService {
    /**
     * Call the provider's {@code token} endpoint and either create or find the user in our database.
     * @param code The code from the user's browser
     * @return The either existing or newly created user
     */
    public UserEntity handleCode(String code);
}
