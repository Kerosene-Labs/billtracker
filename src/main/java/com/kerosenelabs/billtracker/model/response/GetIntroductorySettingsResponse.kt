package com.kerosenelabs.billtracker.model.response

import com.kerosenelabs.billtracker.model.IntroductorySettings
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class GetIntroductorySettingsResponse {
    private var introductorySettings: IntroductorySettings? = null
}
