package com.kerosenelabs.billtracker.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.util.*

/**
 * Represents an actual expense. This could be a one-off (typically indicated by
 * [ExpenseEventEntity.recurringExpenseEventCreator] being `null`).
 */
@Entity
@Table(name = "expense_event", schema = "public")
class ExpenseEventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(nullable = false)
    var amount: BigDecimal = BigDecimal.ZERO,

    @ManyToOne
    @JoinColumn(nullable = false)
    var user: UserEntity = UserEntity(),

    @Column(nullable = false)
    var date: Instant = Instant.now(),

    @Column(nullable = false)
    var description: String = "",

    @ManyToOne
    var recurringExpenseEventCreator: RecurringExpenseEventCreatorEntity? = null
)