package com.kerosenelabs.billtracker.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.ConfirmationTokenEntity;
import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final NoReplyMailService noReplyMailService;
    private final String billTrackerBaseUrl;

    public ConfirmationTokenService(@Value("${billtracker.baseUrl}") String billTrackerBaseUrl,
            ConfirmationTokenRepository confirmationTokenRepository,
            NoReplyMailService noReplyMailService) {
        this.billTrackerBaseUrl = billTrackerBaseUrl;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.noReplyMailService = noReplyMailService;
    }

    /**
     * Create a Confirmation Token for a user. This user will not be allowed to
     * access the system until they've been verified.
     * 
     * @param user The entity to create the confirmation token for
     * @return
     */
    public ConfirmationTokenEntity createAndSendConfirmationToken(UserEntity user) {
        // create our confirmation token
        ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenRepository
                .save(new ConfirmationTokenEntity(user, Instant.now().plusSeconds(900)));

        // build our message
        StringBuilder message = new StringBuilder("Welcome!\n");
        message.append("Click the link below to confirm your account. This is only valid for 15 mintues.\n");
        message.append(String.format("%s/confirm?token=%s", billTrackerBaseUrl, confirmationTokenEntity.getId()));

        // send the mail
        noReplyMailService.sendTestEmail(user.getEmailAddress(), "Confirm your BillTracker account",
                message.toString());
        return confirmationTokenEntity;
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
                .orElseThrow(() -> new AuthException(
                        "Confirmation token not on record for this user; perhapse one was never created?"));
    }

    /**
     * Confirm the user by ensuring the confirmation token given is the one
     * associated with them.
     * 
     * @param userEntity
     * @param potentialTokenId
     * @throws AuthException
     */
    public void confirmUser(String confirmationTokenCandidate) throws AuthException {
        // get our confirmation token, check if this user's already confirmed
        ConfirmationTokenEntity confirmationToken = confirmationTokenRepository
                .findById(UUID.fromString(confirmationTokenCandidate))
                .orElseThrow(() -> new AuthException("Invalid confirmation token"));

        // ensure that our candidate token matches ours
        if (!confirmationToken.getId().toString().equals(confirmationTokenCandidate)) {
            throw new AuthException("Confirmation token candidate does not equal the users confirmation token");
        }

        // update our confirmation token
        confirmationToken.setConfirmed(true);
        confirmationTokenRepository.save(confirmationToken);
    }
}
