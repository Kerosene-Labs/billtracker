package com.kerosenelabs.billtracker.service

import com.kerosenelabs.billtracker.entity.ExpenseEventEntity
import com.kerosenelabs.billtracker.entity.RecurringExpenseEventCreatorEntity
import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.expense.ExpenseEvent
import com.kerosenelabs.billtracker.model.expense.ExpenseEventType
import com.kerosenelabs.billtracker.model.expense.RecurringExpenseEventCreator
import com.kerosenelabs.billtracker.repository.ExpenseEventRepository
import com.kerosenelabs.billtracker.repository.RecurringExpenseEventCreatorRepository
import org.hibernate.sql.ast.tree.expression.Every
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant

@Service
class ExpenseService(
    val expenseEventRepository: ExpenseEventRepository,
    val recurringExpenseEventCreatorRepository: RecurringExpenseEventCreatorRepository
) {

    /**
     * Creates a one-off expense. For example, one might create a one-off expense for purchasing dinner or groceries.
     * @see ExpenseEventEntity
     */
    fun createOneOffExpense(
        amount: BigDecimal,
        user: UserEntity,
        date: Instant,
        description: String
    ): ExpenseEventEntity {
        val expenseEvent = ExpenseEventEntity(
            amount = amount,
            user = user,
            date = date,
            description = description
        )
        return expenseEventRepository.save(expenseEvent)
    }

    /**
     * Creates a Recurring Expense Event Creator. These entities are picked up from scheduled tasks and create expense events on set calendar dates.
     */
    fun createRecurringExpenseEventCreator(
        amount: BigDecimal,
        user: UserEntity,
        recursEveryCalendarDay: Int,
        description: String
    ): RecurringExpenseEventCreatorEntity {
        return RecurringExpenseEventCreatorEntity(
            amount = amount,
            user = user,
            recursEveryCalendarDay = recursEveryCalendarDay,
            description = description
        )
    }

    /**
     * Helper function to get all expense events.
     * @see ExpenseEventEntity
     */
    fun getExpenseEventEntitiesByUser(user: UserEntity): List<ExpenseEventEntity> {
        return expenseEventRepository.findAllByUser(user)
    }

    /**
     * Helper function to get all recurring expense event creators by a user.
     * @see RecurringExpenseEventCreatorEntity
     */
    fun getRecurringExpenseEventCreatorsByUser(user: UserEntity): List<RecurringExpenseEventCreatorEntity> {
        return recurringExpenseEventCreatorRepository.findAllByUser(user)
    }

    /**
     * Map an Expense Event Entity (aka an expense event from the database) to an expense event model, suitable for
     * consumption by a user.
     */
    fun mapExpenseEventEntityToExpenseEvent(expenseEventEntity: ExpenseEventEntity): ExpenseEvent {
        return ExpenseEvent(
            id = expenseEventEntity.id!!,
            date = expenseEventEntity.date,
            expenseEventType = ExpenseEventType.ONE_OFF,
            amount = expenseEventEntity.amount,
            description = expenseEventEntity.description,
        )
    }

    /**
     * Map a RecurringExpenseEventCreatorEntity (aka an recurring expense event from the database) to a model
     * suitable for consumption by the user.
     */
    fun mapRecurringExpenseEventCreatorEntityToRecurringExpenseEventCreator(recurringExpenseEventCreator: RecurringExpenseEventCreator): RecurringExpenseEventCreator {
        return RecurringExpenseEventCreator(
            id = recurringExpenseEventCreator.id!!,
            recursEveryCalendarDays = recurringExpenseEventCreator,
        )
    }
}