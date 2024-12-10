package com.kerosenelabs.billtracker.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.repository.UserRepository;

import com.kerosenelabs.billtracker.exception.AuthException;
import jakarta.servlet.http.HttpSession;

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

    public boolean doesPasswordMatch(String first, String second) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(first, second);
    }

    /**
     * Create a new user.
     * 
     * @param emailAddress
     * @param password
     * @return The newly created entity
     */
    public UserEntity createUser(String emailAddress, String password) {
        UserEntity userEntity = new UserEntity(emailAddress, getKeyFromPassword(password));
        return userRepository.save(userEntity);
    }

    /**
     * Get a user by their email and password.
     * 
     * @param emailAddress
     * @param password     Plain text password
     * @return Optionally, the user if they were found.
     * @throws AuthException
     */
    public UserEntity getUserByEmailAndPassword(String emailAddress, String password) throws AuthException {
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
}
