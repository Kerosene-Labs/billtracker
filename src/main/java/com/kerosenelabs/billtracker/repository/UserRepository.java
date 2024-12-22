package com.kerosenelabs.billtracker.repository;

import java.util.Optional;
import java.util.UUID;

import com.kerosenelabs.billtracker.model.OAuth2Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kerosenelabs.billtracker.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public Optional<UserEntity> findUserByEmailAddress(String emailAddress);
    public Optional<UserEntity> findById(UUID id);
    public Optional<UserEntity> findBySubAndProvider(String sub, OAuth2Provider provider);
}
