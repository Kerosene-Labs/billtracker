package com.kerosenelabs.billtracker.model.response

import com.kerosenelabs.billtracker.model.expense.ExpenseEvent

data class GetExpenseEventsResponse(
    private val expenseEvents: List<ExpenseEvent>
)