package com.kerosenelabs.billtracker.model.response

import com.kerosenelabs.billtracker.model.expense.RecurringExpenseEventCreator

data class GetRecurringExpenseEventCreatorsResponse(
    val recurringExpenseEventCreators: List<RecurringExpenseEventCreator>
)