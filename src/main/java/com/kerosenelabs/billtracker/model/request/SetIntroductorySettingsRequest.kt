package com.kerosenelabs.billtracker.model.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import java.time.LocalDate

data class SetIntroductorySettingsRequest(
    @NotEmpty @NotNull @Length(min = 1, max = 30) val firstName: String,
    @NotEmpty @NotNull @Length(min = 1, max = 30) val lastName: String,
    @NotEmpty @NotNull @Length(min = 1, max = 30) val birthday: LocalDate,
)