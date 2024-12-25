package com.kerosenelabs.billtracker.model.response

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class ValidateSessionResponse {
    private var valid = false
}
