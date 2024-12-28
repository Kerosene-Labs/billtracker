package com.kerosenelabs.billtracker.model.expense

import java.math.BigDecimal
import java.util.UUID

data class RecurringExpenseEventCreator(
    var id: UUID,
    var recursEveryCalendarDay: Int,
    var amount: BigDecimal,
    var description: String,
)
