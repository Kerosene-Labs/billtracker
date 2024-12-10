package com.kerosenelabs.billtracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kerosenelabs.billtracker.entity.ConfirmationTokenEntity;
import com.kerosenelabs.billtracker.entity.UserEntity;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, UUID> {
    Optional<ConfirmationTokenEntity> findByUser(UserEntity user);
}
