package com.kerosenelabs.billtracker.model.response

import com.kerosenelabs.billtracker.model.expense.ExpenseEvent

data class GetExpenseEventsResponse(
    val expenseEvents: List<ExpenseEvent>
)