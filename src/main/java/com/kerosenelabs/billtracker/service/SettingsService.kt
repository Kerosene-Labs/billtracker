package com.kerosenelabs.billtracker.service;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.model.IntroductorySettings;
import com.kerosenelabs.billtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SettingsService {
    private final UserRepository userRepository;

    public SettingsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Set the users Introductory Settings
     * @param userEntity
     * @param firstName
     * @param lastName
     * @param birthday
     */
    public void setIntroductorySettings(UserEntity userEntity, String firstName, String lastName, LocalDate birthday) {
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setBirthday(birthday);
        userRepository.save(userEntity);
    }

    /**
     * Helper to get the user's introductory settings.
     * @param user
     * @return
     */
    public IntroductorySettings getIntroductorySettings(UserEntity user) {
        return new IntroductorySettings(user.getFirstName(), user.getLastName(), user.getBirthday());
    }
}
