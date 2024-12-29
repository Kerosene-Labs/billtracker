package com.kerosenelabs.billtracker.controller

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.request.CreateOneOffExpenseRequest
import com.kerosenelabs.billtracker.model.request.CreateRecurringExpenseCreatorRequest
import com.kerosenelabs.billtracker.model.response.GetExpenseEventsResponse
import com.kerosenelabs.billtracker.model.response.GetRecurringExpenseEventCreatorsResponse
import com.kerosenelabs.billtracker.service.ExpenseService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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
    fun getRecurringExpenseCreators(@Parameter(hidden = true) user: UserEntity): GetRecurringExpenseEventCreatorsResponse {
        return GetRecurringExpenseEventCreatorsResponse(
            expenseService.getRecurringExpenseEventCreatorsByUser(user)
                .stream()
                .map { entity -> expenseService.mapRecurringExpenseEventCreatorEntityToRecurringExpenseEventCreator(entity) }
                .toList()
        )
    }
}
