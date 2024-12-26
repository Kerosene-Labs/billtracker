package com.kerosenelabs.billtracker.service

import com.kerosenelabs.billtracker.entity.ExpenseEventEntity
import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.expense.ExpenseEvent
import com.kerosenelabs.billtracker.model.expense.ExpenseEventType
import com.kerosenelabs.billtracker.repository.ExpenseEventRepository
import com.kerosenelabs.billtracker.repository.RecurringExpenseEventCreatorRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant

@Service
class ExpenseService(
    val expenseEventRepository: ExpenseEventRepository,
    val expenseEventCreatorRepository: RecurringExpenseEventCreatorRepository
) {

    /**
     * Creates a one-off expense. For example, one might create a one-off expense for purchasing dinner or groceries.
     * @see ExpenseEventEntity
     */
    fun createOneOffExpense(amount: BigDecimal, user: UserEntity, date: Instant, description: String): ExpenseEventEntity {
        val expenseEvent = ExpenseEventEntity(
            amount = amount,
            user = user,
            date = date,
            description = description
        )
        return expenseEventRepository.save(expenseEvent)
    }

    /**
     * Helper function to get all expense events.
     * @see ExpenseEventEntity
     */
    fun getExpenseEventEntitiesByUser(user: UserEntity): List<ExpenseEventEntity> {
        return expenseEventRepository.findExpenseEventEntitiesByUser(user)
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
}