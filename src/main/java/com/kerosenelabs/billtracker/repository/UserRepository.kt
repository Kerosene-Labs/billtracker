package com.kerosenelabs.billtracker.repository

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.OAuth2Provider
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity?, UUID?> {
    fun findUserByEmailAddress(emailAddress: String?): Optional<UserEntity?>?
    fun findById(id: UUID): Optional<UserEntity?>
    fun findBySubAndProvider(sub: String?, provider: OAuth2Provider?): Optional<UserEntity?>?
}
