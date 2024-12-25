package com.kerosenelabs.billtracker.service

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.exception.AuthException
import org.springframework.stereotype.Service
import java.io.IOException

@Service
interface OAuth2ProviderService {
    /**
     * Call the provider's `token` endpoint and either create or find the user in our database.
     * @param code The code from the user's browser
     * @return The either existing or newly created user
     */
    @Throws(IOException::class, AuthException::class)
    fun handleCode(code: String): UserEntity?
}
