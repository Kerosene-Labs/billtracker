package com.kerosenelabs.billtracker.model.response;

import com.kerosenelabs.billtracker.model.IntroductorySettings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIntroductorySettingsResponse {
    private IntroductorySettings introductorySettings;
}
