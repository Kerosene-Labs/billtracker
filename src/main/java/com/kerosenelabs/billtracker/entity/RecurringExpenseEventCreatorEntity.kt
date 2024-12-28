package com.kerosenelabs.billtracker.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "recurring_expense_event_creator", schema = "public")
class RecurringExpenseEventCreatorEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID? = null,
    @Column(nullable = false) var recursEveryDays: Int = 0,
    @Column(nullable = false) var amount: BigDecimal = BigDecimal.ZERO,

    @ManyToOne
    @JoinColumn(nullable = false)
    var user: UserEntity = UserEntity(),

    @Column(nullable = false) var description: String = "",
)