package com.kerosenelabs.billtracker.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "confirmation_token", schema = "public")
@Entity
public class ConfirmationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(unique = true)
    private UserEntity user;

    @Column(nullable = false)
    private Instant expires;

    @Column(nullable = false)
    private boolean confirmed = false;

    public ConfirmationTokenEntity(UserEntity user, Instant expires) {
        this.user = user;
        this.expires = expires;
    }
}
