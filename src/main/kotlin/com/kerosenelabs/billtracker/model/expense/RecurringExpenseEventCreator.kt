package com.kerosenelabs.billtracker.model.expense

import java.math.BigDecimal

data class RecurringExpenseEventCreator(
    var recursEveryCalendarDay: Int,
    var amount: BigDecimal,
    var description: String,
)
