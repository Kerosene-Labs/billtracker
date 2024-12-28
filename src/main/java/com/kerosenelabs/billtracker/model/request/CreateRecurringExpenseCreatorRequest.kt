package com.kerosenelabs.billtracker.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class CreateRecurringExpenseCreatorRequest(
    @field:Positive val recursEveryDays: Int,
    @field:Positive val amount: BigDecimal,
    @field:NotBlank val description: String,
)
