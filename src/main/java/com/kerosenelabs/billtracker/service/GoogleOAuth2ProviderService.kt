package com.kerosenelabs.billtracker.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.exception.AuthException
import com.kerosenelabs.billtracker.model.OAuth2Provider
import com.kerosenelabs.billtracker.model.external.response.GoogleOAuthTokenResponse
import com.kerosenelabs.billtracker.model.external.response.GoogleOAuthUserInfoResponse
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*

@Service
class GoogleOAuth2ProviderService(
    @param:Value("\${billtracker.oauth2.providers.google.tokenEndpoint}") val tokenEndpoint: String,
    @param:Value("\${billtracker.oauth2.providers.google.clientId}") private val clientId: String,
    @param:Value("\${billtracker.oauth2.providers.google.clientSecret}") private val clientSecret: String,
    @param:Value("\${billtracker.oauth2.providers.google.redirectUri}") private val redirectUri: String,
    @param:Value("\${billtracker.oauth2.providers.google.userInfoEndpoint}") private val userInfoEndpoint: String,
    private val userService: UserService
) : OAuth2ProviderService {
    @Throws(IOException::class, AuthException::class)
    private fun getTokenResponse(code: String): GoogleOAuthTokenResponse {
        val client = OkHttpClient()
        val body: RequestBody = FormBody.Builder()
            .add("client_id", clientId)
            .add("client_secret", clientSecret)
            .add("code", code)
            .add("grant_type", "authorization_code")
            .add("redirect_uri", redirectUri)
            .build()
        val request = Request.Builder()
            .url(tokenEndpoint)
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            val bodyContent = response.body()!!.string()
            if (!response.isSuccessful) {
                throw AuthException("Got error response from provider: $bodyContent")
            }
            val mapper = ObjectMapper()
            val tokenResponse = mapper.readValue(
                bodyContent,
                GoogleOAuthTokenResponse::class.java
            )
            return tokenResponse
        }
    }

    @Throws(IOException::class)
    private fun getUserInfoFromProvider(accessToken: String): GoogleOAuthUserInfoResponse {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(userInfoEndpoint)
            .addHeader("Authorization", String.format("Bearer %s", accessToken))
            .build()
        client.newCall(request).execute().use { response ->
            val mapper = ObjectMapper()
            return mapper.readValue(response.body()!!.string(), GoogleOAuthUserInfoResponse::class.java)
        }
    }

    @Throws(IOException::class, AuthException::class)
    override fun handleCode(code: String): UserEntity {
        // get our token response from Google
        val oAuthTokenResponse = getTokenResponse(code)

        // get our email from the provider
        val userInfo = getUserInfoFromProvider(oAuthTokenResponse.accessToken)

        // fetch our user or create them
        var userEntity: Optional<UserEntity?> = Optional.empty()
        try {
            userEntity =
                Optional.of<UserEntity?>(userService.getUserBySubAndProvider(userInfo.getSub(), OAuth2Provider.GOOGLE))
        } catch (e: AuthException) {
            userEntity = Optional.of<UserEntity?>(
                userService.createUser(
                    userInfo.getEmail(),
                    userInfo.getSub(),
                    OAuth2Provider.GOOGLE,
                    userInfo.getGivenName(),
                    userInfo.getFamilyName()
                )
            )
        }
        return userEntity.orElseThrow {
            AuthException(
                "Undefined behavior, User was not found and was not created."
            )
        }
    }
}
