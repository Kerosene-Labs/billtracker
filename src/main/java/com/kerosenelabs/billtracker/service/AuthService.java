package com.kerosenelabs.billtracker.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.repository.UserRepository;

import com.kerosenelabs.billtracker.exception.AuthException;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    public AuthService(UserRepository userRepository, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
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

    public boolean doesPasswordMatch(String first, String second) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(first, second);
    }

    /**
     * Create a new user. Also creates a confirmation token and sends an email out
     * for confirmation.
     * 
     * @param emailAddress
     * @param password
     * @return The newly created entity
     */
    public UserEntity createUser(String emailAddress, String password) {
        UserEntity userEntity = new UserEntity(emailAddress, getKeyFromPassword(password));
        userRepository.save(userEntity);
        confirmationTokenService.createAndSendConfirmationToken(userEntity);
        return userEntity;
    }

    /**
     * Get a user by their email.
     * 
     * @param emailAddress
     * @return Optionally, the user if they were found.
     * @throws AuthException
     */
    public UserEntity getUserByEmail(String emailAddress) throws AuthException {
        return userRepository.findUserByEmailAddress(emailAddress).orElseThrow(
                () -> new AuthException("A user with the given credentials could not be found."));
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
     * Establish a session for the given user.
     * 
     * @param userEntity
     */
    public void establishSession(HttpSession httpSession, UserEntity userEntity) {
        httpSession.setAttribute("userId", userEntity.getId());
    }

    /**
     * Sets introductory settings such as the users first and last name
     */
    public void setIntroductorySettings(UserEntity userEntity, String firstName, String lastName, LocalDate birthday) {
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setBirthday(birthday);
        userRepository.save(userEntity);
    }

    public void endSession(HttpSession httpSession) {
        httpSession.removeAttribute("userId");
    }
}
