package com.kerosenelabs.billtracker.model.expense

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class ExpenseEvent(
    val id: UUID,
    val amount: BigDecimal,
    val date: Instant,
    val expenseEventType: ExpenseEventType
)
