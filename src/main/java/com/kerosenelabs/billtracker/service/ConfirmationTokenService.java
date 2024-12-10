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
    public ConfirmationTokenEntity createConfirmationToken(UserEntity user) {
        return confirmationTokenRepository
                .save(new ConfirmationTokenEntity(user, Instant.now().plusSeconds(900)));
    }

    /**
     * Get a confirmation token by their user.
     * 
     * @param user
     * @return
     * @throws AuthException
     */
    public ConfirmationTokenEntity getConfirmationTokenByUser(UserEntity user) throws AuthException {
        return confirmationTokenRepository.findByUser(user)
                .orElseThrow(() -> new AuthException("Confirmation token not on record for this user; perhapse one was never created?"));
    }

    /**
     * Confirm the user by ensuring the confirmation token given is the one
     * associated with them.
     * 
     * @param userEntity
     * @param potentialTokenId
     * @throws AuthException
     */
    public void confirmUser(UserEntity userEntity, String confirmationTokenCandidate) throws AuthException {
        // get our confirmation token, check if this user's already confirmed
        ConfirmationTokenEntity confirmationToken = getConfirmationTokenByUser(userEntity);
        if (getConfirmationTokenByUser(userEntity).isConfirmed()) {
            throw new AuthException("User already confirmed");
        }

        // ensure that our candidate token matches ours
        if (!confirmationToken.getId().toString().equals(confirmationTokenCandidate)) {
            throw new AuthException("Confirmation token candidate does not equal the users confirmation token");
        }
    }
}
