package com.kerosenelabs.billtracker.model

/**
 * Represents the Recurring Types of expenses or income. For example,
 * your mortgage might be billed on the first of every month, but you
 * might get paid every two weeks.
 */
enum class RecurringType {
    DATE,
    INTERVAL
}
