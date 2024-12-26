package com.kerosenelabs.billtracker.model.request

import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.Instant

data class CreateOneOffExpenseRequest(
    @field:Positive val amount: BigDecimal,
    val date: Instant,
)