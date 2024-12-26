package com.kerosenelabs.billtracker.entity

import jakarta.persistence.*
import java.util.*

/**
 * `RecurringExpenseEventCreators` are representations of expense events that recur on a regular basis. For example, a
 * Recurring Expense Event might be a bill that comes out on the first of every month, like a mortgage. Another example
 * is an event that occurs every thirty days, like a power bill. If you have a one-off expense, you would add an
 * [ExpenseEventEntity] directly. The application will run a scheduled task in Spring every minute to check for
 * occurrences and will create the [ExpenseEventEntity] for these events.
 */
@Entity
@Table(name = "recurring_expense_event_creator", schema = "public")
class RecurringExpenseEventCreatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: UUID? = null
}
