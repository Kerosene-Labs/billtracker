package com.kerosenelabs.billtracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * RecurringExpenseEventCreators are creators that create expense events on a recurring basis. For example, a
 * Recurring Expense Event might be a bill that comes out on the first of every month, like a mortgage. Another example
 * is an event that occurs every thirty days, like a power bill. If you have a one-off expense, you would add an
 * {@link ExpenseEventEntity} directly.
 */
@Entity
public class RecurringExpenseEventCreatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

}
