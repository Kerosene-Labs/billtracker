package com.kerosenelabs.billtracker.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@AllArgsConstructor
@NoArgsConstructor
class IntroductorySettings {
    private var firstName: String? = null
    private var lastName: String? = null
    private var birthday: LocalDate? = null
}
