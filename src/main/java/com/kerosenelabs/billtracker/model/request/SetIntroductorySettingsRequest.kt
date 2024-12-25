package com.kerosenelabs.billtracker.model.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.validator.constraints.Length
import java.time.LocalDate

@Data
@AllArgsConstructor
@NoArgsConstructor
class SetIntroductorySettingsRequest {
    private var firstName: @NotEmpty @NotNull @Length(min = 1, max = 30) String? = null

    private var lastName: @NotEmpty @NotNull @Length(min = 1, max = 30) String? = null

    private var birthday: @NotEmpty @NotNull @Length(min = 1, max = 30) LocalDate? = null
}
