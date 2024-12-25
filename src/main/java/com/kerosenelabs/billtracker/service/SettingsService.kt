package com.kerosenelabs.billtracker.service

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.IntroductorySettings
import com.kerosenelabs.billtracker.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SettingsService(private val userRepository: UserRepository) {
    /**
     * Set the users Introductory Settings
     * @param userEntity
     * @param firstName
     * @param lastName
     * @param birthday
     */
    fun setIntroductorySettings(userEntity: UserEntity, firstName: String?, lastName: String?, birthday: LocalDate?) {
        userEntity.firstName = firstName
        userEntity.lastName = lastName
        userEntity.birthday = birthday
        userRepository.save(userEntity)
    }

    /**
     * Helper to get the user's introductory settings.
     * @param user
     * @return
     */
    fun getIntroductorySettings(user: UserEntity): IntroductorySettings {
        return IntroductorySettings(user.firstName, user.lastName, user.birthday)
    }
}
