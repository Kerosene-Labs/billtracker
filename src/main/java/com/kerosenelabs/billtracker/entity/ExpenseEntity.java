package com.kerosenelabs.billtracker.entity;

import com.kerosenelabs.billtracker.model.RecurringType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "expense")
@Entity
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private UserEntity owner;

    @Column(nullable = false)
    private RecurringType recurringType;
}
