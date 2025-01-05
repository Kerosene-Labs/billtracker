package com.kerosenelabs.billtracker.controller

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.exception.BadRequestException
import com.kerosenelabs.billtracker.model.request.CreateOneOffExpenseRequest
import com.kerosenelabs.billtracker.model.request.CreateRecurringExpenseCreatorRequest
import com.kerosenelabs.billtracker.model.response.GetExpenseEventsResponse
import com.kerosenelabs.billtracker.model.response.GetRecurringExpenseEventCreatorsResponse
import com.kerosenelabs.billtracker.service.ExpenseService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Tag(name = "Expenses", description = "Personal expenses")
class ExpensesController(private val expenseService: ExpenseService) {
    @PostMapping("/expenses/oneOffs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun createOneOff(
        @Parameter(hidden = true) user: UserEntity,
        @Valid @RequestBody request: CreateOneOffExpenseRequest,
    ) {
        expenseService.createOneOffExpense(request.amount, user, request.date, request.description)
    }

    @PostMapping("/expenses/recurringCreators")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun createRecurringExpenseCreator(
        @Parameter(hidden = true) user: UserEntity,
        @Valid @RequestBody request: CreateRecurringExpenseCreatorRequest
    ) {
        expenseService.createRecurringExpenseEventCreator(
            request.amount,
            user,
            request.recursEveryCalendarDay,
            request.description
        )
    }

    @PutMapping("/expenses/recurringCreators/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun supersedeRecurringExpenseCreator(
        @Parameter(hidden = true) user: UserEntity,
        @PathVariable("id", required = true) id: String,
        @RequestBody request: CreateRecurringExpenseCreatorRequest
    ) {
        val recurringExpenseEventCreator =
            expenseService.getRecurringExpenseEventCreatorsByUser(user).find { it.id == UUID.fromString(id) }
                ?: throw BadRequestException("Invalid ID")
        expenseService.supersedeRecurringExpenseEventCreator(
            predecessor = recurringExpenseEventCreator,
            amount = request.amount,
            description = request.description,
            recursEveryCalendarDay = request.recursEveryCalendarDay,
        )
    }

    @DeleteMapping("/expenses/recurringCreators/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRecurringExpenseCreator(
        @Parameter(hidden = true) user: UserEntity,
        @PathVariable("id", required = true) id: String,
    ) {
        val recurringExpenseEventCreator =
            expenseService.getRecurringExpenseEventCreatorsByUser(user).find { it.id == UUID.fromString(id) }
                ?: throw BadRequestException("Invalid ID")
        expenseService.hideRecurringExpenseEventCreator(recurringExpenseEventCreator)
    }

    @GetMapping("/expenses")
    @ResponseStatus(HttpStatus.OK)
    fun getExpenses(@Parameter(hidden = true) user: UserEntity): GetExpenseEventsResponse {
        return GetExpenseEventsResponse(
            expenseService.getExpenseEventEntitiesByUser(user)
                .stream()
                .map { entity -> expenseService.mapExpenseEventEntityToExpenseEvent(entity) }
                .toList()
        )
    }

    @GetMapping("/expenses/recurringCreators")
    @ResponseStatus(HttpStatus.OK)
    fun getRecurringExpenseCreators(
        @Parameter(hidden = true) user: UserEntity,
        @RequestParam(name = "ids", required = false) ids: List<UUID>?
    ): GetRecurringExpenseEventCreatorsResponse {
        return GetRecurringExpenseEventCreatorsResponse(
            expenseService.getRecurringExpenseEventCreatorsByUser(user, Optional.ofNullable(ids))
                .stream()
                .map { entity ->
                    expenseService.mapRecurringExpenseEventCreatorEntityToRecurringExpenseEventCreator(
                        entity
                    )
                }
                .toList()
        )
    }

}
