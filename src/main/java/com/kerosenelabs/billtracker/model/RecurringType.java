package com.kerosenelabs.billtracker.model;

/**
 * Represents the Recurring Types of expenses or income. For example,
 * your mortgage might be billed on the first of every month, but you
 * might get paid every two weeks.
 */
public enum RecurringType {
    FIXED_DATE,
    FIXED_INTERVAL
}
