package com.kerosenelabs.billtracker.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.ConfirmationTokenEntity;
import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.repository.ConfirmationTokenRepository;

import jakarta.security.auth.message.AuthException;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    /**
     * Create a Confirmation Token for a user. This user will not be allowed to
     * access the system until they've been verified.
     * 
     * @param userEntity The entity to create the confirmation token for
     * @return
     */
    public ConfirmationTokenEntity createConfirmationToken(UserEntity userEntity) {
        return confirmationTokenRepository
                .save(new ConfirmationTokenEntity(userEntity, Instant.now().plusSeconds(900)));
    }

    /**
     * Check if a user has confirmed their account or not.
     * 
     * @param userEntity
     * @return
     * @throws AuthException
     */
    public boolean isUserConfirmed(UserEntity userEntity) throws AuthException {
        ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenRepository.findByUser(userEntity)
                .orElseThrow(() -> new AuthException("Unverified account, please check your e-mail"));
        return confirmationTokenEntity.isConfirmed();
    }

}
