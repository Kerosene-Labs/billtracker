package com.kerosenelabs.billtracker.model.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class CreateRecurringExpenseCreatorRequest(
    @field:Min(1) @field:Max(28) val recursEveryDays: Int,
    @field:Positive val amount: BigDecimal,
    @field:NotBlank val description: String,
)
