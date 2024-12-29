package com.kerosenelabs.billtracker.entity

import com.kerosenelabs.billtracker.model.expense.RecurringExpenseEventCreator
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "recurring_expense_event_creator", schema = "public")
class RecurringExpenseEventCreatorEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID? = null,
    @Column(nullable = false) var recursEveryCalendarDay: Int = 0,
    @Column(nullable = false) var amount: BigDecimal = BigDecimal.ZERO,
    @ManyToOne @JoinColumn(nullable = false) var user: UserEntity = UserEntity(),
    @OneToOne var supersededBy: RecurringExpenseEventCreatorEntity? = null,
    @Column(nullable = false) var description: String = "",
) {
    constructor(
        recursEveryCalendarDay: Int, amount: BigDecimal, user: UserEntity, description: String
    ) : this(
        id = null,
        recursEveryCalendarDay = recursEveryCalendarDay,
        amount = amount,
        user = user,
        supersededBy = null,
        description = description
    )
}