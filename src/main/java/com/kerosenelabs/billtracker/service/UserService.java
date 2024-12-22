package com.kerosenelabs.billtracker.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.repository.UserRepository;

import com.kerosenelabs.billtracker.exception.AuthException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get a key suitable for persistence to a database from a password.
     * 
     * @param password The user provided password
     * @return The key
     */
    public String getKeyFromPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = password;
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Checks if the given passwords match using the BCrypt algorithm
     * @param first
     * @param second
     * @return True if matches, false if doesn't
     */
    public boolean doesPasswordMatch(String first, String second) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(first, second);
    }

    /**
     * Create a new user. Also creates a confirmation token and sends an email out
     * for confirmation.
     * 
     * @param emailAddress
     * @param sub The OAuth/OpenID sub token
     * @return The newly created entity
     */
    public UserEntity createUser(String emailAddress, String sub, String firstName, String lastName) {
        UserEntity userEntity = new UserEntity(emailAddress, sub, firstName, lastName);
        userRepository.save(userEntity);
        return userEntity;
    }

    /**
     * Get a user by their ID
     * 
     * @param id
     * @return
     * @throws AuthException
     */
    public UserEntity getUserById(UUID id) throws AuthException {
        return userRepository.findById(id).orElseThrow(
                () -> new AuthException("A user with that ID could not be found."));
    }

    /**
     * Get a user by their OpenID ID Token
     *
     * @param sub
     * @return
     * @throws AuthException
     */
    public UserEntity getUserBySub(String sub) throws AuthException {
        return userRepository.findBySub(sub).orElseThrow(
                () -> new AuthException("A user with that sub token could not be found."));
    }
}
