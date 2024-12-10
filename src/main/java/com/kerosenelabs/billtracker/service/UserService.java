package com.kerosenelabs.billtracker.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.repository.UserRepository;

import jakarta.security.auth.message.AuthException;
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

    /**
     * Create a new user.
     * 
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param password
     * @return The newly created entity
     */
    public UserEntity createUser(String firstName, String lastName, String emailAddress, String password) {
        UserEntity userEntity = new UserEntity(firstName, lastName, emailAddress, getKeyFromPassword(password));
        return userRepository.save(userEntity);
    }

    /**
     * Get a user by their email and password.
     * 
     * @param emailAddress
     * @param password     Plain text password
     * @return Optionally, the user if they were found.
     */
    public Optional<UserEntity> getUserByEmailAndPassword(String emailAddress, String password) {
        return userRepository.findUserByEmailAndPassword(emailAddress, getKeyFromPassword(password));
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
