package com.kerosenelabs.billtracker.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "user", schema = "public")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName = null;
    private String lastName = null;
    private LocalDate birthday = null;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false, unique = true)
    private String idToken;

    @Column(nullable = false, unique = true)
    private String accessToken;

    public UserEntity(String emailAddress, String idToken) {
        this.emailAddress = emailAddress;
        this.idToken = idToken;
    }
}