package com.kerosenelabs.billtracker.model.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class HandleTokenRequest {
    private var code: String? = null
}
