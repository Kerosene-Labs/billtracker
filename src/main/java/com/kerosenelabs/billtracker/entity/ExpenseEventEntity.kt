package com.kerosenelabs.billtracker.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.math.BigDecimal
import java.time.Instant
import java.util.*

/**
 * Represents an actual expense. This could be a one-off (typically indicated by
 * [ExpenseEventEntity.recurringExpenseEventCreator] being `null`).
 */
@Entity
class ExpenseEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: UUID? = null

    @Column(nullable = false)
    private var amount: BigDecimal? = null

    @ManyToOne
    @JoinColumn(nullable = false)
    private var user: UserEntity? = null

    @Column(nullable = false)
    private var date: Instant? = null

    @ManyToOne
    private var recurringExpenseEventCreator: RecurringExpenseEventCreatorEntity? = null
}