package com.kerosenelabs.billtracker.service;

import java.util.UUID;

import com.kerosenelabs.billtracker.model.OAuth2Provider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.repository.UserRepository;

import com.kerosenelabs.billtracker.exception.AuthException;

import javax.crypto.spec.SecretKeySpec;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final String signingKey;

    public UserService(@Value("${billtracker.signingKey}") String signingKey, UserRepository userRepository) {
        this.signingKey = signingKey;
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
     *
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
     * @param sub          The OAuth/OpenID sub token
     * @return The newly created entity
     */
    public UserEntity createUser(String emailAddress, String sub, OAuth2Provider provider, String firstName, String lastName) {
        UserEntity userEntity = new UserEntity(emailAddress, sub, provider, firstName, lastName);
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
     * Get a user by their OpenID Connect Subject
     *
     * @param sub
     * @param provider The OAuth2 / OpenID Provider
     * @return
     * @throws AuthException
     */
    public UserEntity getUserBySubAndProvider(String sub, OAuth2Provider provider) throws AuthException {
        return userRepository.findBySubAndProvider(sub, provider).orElseThrow(
                () -> new AuthException("A user with that subject token on that provider could not be found."));
    }

    public String establishJwt(UserEntity user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("provider", user.getProvider())
                .signWith(new SecretKeySpec(signingKey.getBytes(), "HmacSHA256"))
                .compact();
    }

    private Claims getClaimsFromToken(String jwt) {
        return Jwts.parser()
                .verifyWith(new SecretKeySpec(signingKey.getBytes(), "HmacSHA256"))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public String getIdFromJwt(String jwt) {
        return getClaimsFromToken(jwt).getSubject();
    }

    public OAuth2Provider getProviderFromJwt(String jwt) {
        return OAuth2Provider.valueOf((String) getClaimsFromToken(jwt).get("provider"));
    }
}
