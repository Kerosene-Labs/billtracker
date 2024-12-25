package com.kerosenelabs.billtracker.service

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.exception.AuthException
import com.kerosenelabs.billtracker.model.OAuth2Provider
import com.kerosenelabs.billtracker.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Supplier
import javax.crypto.spec.SecretKeySpec

@Service
class UserService(
    @param:Value("\${billtracker.signingKey}") private val signingKey: String,
    private val userRepository: UserRepository
) {
    /**
     * Get a key suitable for persistence to a database from a password.
     *
     * @param password The user provided password
     * @return The key
     */
    fun getKeyFromPassword(password: String): String {
        val passwordEncoder = BCryptPasswordEncoder()
        val rawPassword = password
        return passwordEncoder.encode(rawPassword)
    }

    /**
     * Checks if the given passwords match using the BCrypt algorithm
     *
     * @param first
     * @param second
     * @return True if matches, false if doesn't
     */
    fun doesPasswordMatch(first: String?, second: String?): Boolean {
        val passwordEncoder = BCryptPasswordEncoder()
        return passwordEncoder.matches(first, second)
    }

    /**
     * Create a new user. Also creates a confirmation token and sends an email out
     * for confirmation.
     *
     * @param emailAddress
     * @param sub          The OAuth/OpenID sub token
     * @return The newly created entity
     */
    fun createUser(
        emailAddress: String,
        sub: String,
        provider: OAuth2Provider,
        firstName: String?,
        lastName: String?
    ): UserEntity {
        val userEntity = UserEntity(
            id = null,
            emailAddress = emailAddress,
            sub = sub,
            provider = provider,
            firstName = firstName,
            lastName = lastName
        )
        userRepository.save(userEntity)
        return userEntity
    }

    /**
     * Get a user by their ID
     *
     * @param id
     * @return
     * @throws AuthException
     */
    @Throws(AuthException::class)
    fun getUserById(id: UUID): UserEntity {
        return userRepository.findById(id).orElseThrow {
            AuthException(
                "A user with that ID could not be found."
            )
        }!!
    }

    /**
     * Get a user by their OpenID Connect Subject
     *
     * @param sub
     * @param provider The OAuth2 / OpenID Provider
     * @return
     * @throws AuthException
     */
    @Throws(AuthException::class)
    fun getUserBySubAndProvider(sub: String?, provider: OAuth2Provider?): UserEntity {
        return userRepository.findBySubAndProvider(sub, provider)?.orElseThrow(
            Supplier { AuthException("A user with that subject token on that provider could not be found.") })!!
    }

    fun establishJwt(user: UserEntity): String {
        return Jwts.builder()
            .subject(user.id.toString())
            .claim("provider", user.provider)
            .signWith(SecretKeySpec(signingKey.toByteArray(), "HmacSHA256"))
            .compact()
    }

    private fun getClaimsFromToken(jwt: String): Claims {
        return Jwts.parser()
            .verifyWith(SecretKeySpec(signingKey.toByteArray(), "HmacSHA256"))
            .build()
            .parseSignedClaims(jwt)
            .payload
    }

    fun getIdFromJwt(jwt: String): String {
        return getClaimsFromToken(jwt).subject
    }

    fun getProviderFromJwt(jwt: String): OAuth2Provider {
        return OAuth2Provider.valueOf((getClaimsFromToken(jwt)["provider"] as String?)!!)
    }
}
