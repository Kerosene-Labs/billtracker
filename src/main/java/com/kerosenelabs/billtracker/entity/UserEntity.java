package com.kerosenelabs.billtracker.entity;

import java.util.UUID;

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

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    public UserEntity(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
}