package com.kerosenelabs.billtracker.service

import com.kerosenelabs.billtracker.entity.ExpenseEventEntity
import com.kerosenelabs.billtracker.entity.RecurringExpenseEventCreatorEntity
import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.expense.ExpenseEvent
import com.kerosenelabs.billtracker.model.expense.ExpenseEventType
import com.kerosenelabs.billtracker.model.expense.RecurringExpenseEventCreator
import com.kerosenelabs.billtracker.repository.ExpenseEventRepository
import com.kerosenelabs.billtracker.repository.RecurringExpenseEventCreatorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


@Service
class ExpenseService(
    private val expenseEventRepository: ExpenseEventRepository,
    private val recurringExpenseEventCreatorRepository: RecurringExpenseEventCreatorRepository,
) {
    private val logger: Logger = LoggerFactory.getLogger(ExpenseService::class.java)

    /**
     * Creates a one-off expense. For example, one might create a one-off expense for purchasing dinner or groceries.
     * @see ExpenseEventEntity
     */
    fun createOneOffExpense(
        amount: BigDecimal, user: UserEntity, date: Instant, description: String
    ): ExpenseEventEntity {
        val expenseEvent = ExpenseEventEntity(
            amount = amount, user = user, date = date, description = description
        )
        return expenseEventRepository.save(expenseEvent)
    }

    /**
     * Creates a Recurring Expense Event Creator. These entities are picked up from scheduled tasks and create expense events on set calendar dates.
     */
    fun createRecurringExpenseEventCreator(
        amount: BigDecimal, user: UserEntity, recursEveryCalendarDay: Int, description: String
    ): RecurringExpenseEventCreatorEntity {
        return recurringExpenseEventCreatorRepository.save(
            RecurringExpenseEventCreatorEntity(
                amount = amount, user = user, recursEveryCalendarDay = recursEveryCalendarDay, description = description
            )
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
    fun mapRecurringExpenseEventCreatorEntityToRecurringExpenseEventCreator(entity: RecurringExpenseEventCreatorEntity): RecurringExpenseEventCreator {
        return RecurringExpenseEventCreator(
            id = entity.id!!,
            description = entity.description,
            amount = entity.amount,
            recursEveryCalendarDay = entity.recursEveryCalendarDay,
        )
    }

    /**
     * Scheduled task that iterates over all recurring expense creators, searching for those that occur today and need posting.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    fun postRecurringExpenses() {
        logger.info("Starting post of recurring expenses")
        val creators = recurringExpenseEventCreatorRepository.findAll();
        val now = LocalDate.now();
        val zoneId = ZoneId.systemDefault()
        for (creator in creators) {
            if (creator.recursEveryCalendarDay == now.dayOfMonth) {
                logger.info("Posting Expense Event: ${creator.amount} - ${creator.description}");
                expenseEventRepository.save(ExpenseEventEntity(
                    amount = creator.amount,
                    description = creator.description,
                    recurringExpenseEventCreator = creator,
                    date = now.atStartOfDay(zoneId).toInstant()
                ))
            }
        }
    }
}