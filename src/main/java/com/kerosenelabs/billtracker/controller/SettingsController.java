package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.model.request.SetIntroductorySettingsRequest;
import com.kerosenelabs.billtracker.model.response.GetIntroductorySettingsResponse;
import com.kerosenelabs.billtracker.service.SettingsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Settings", description = "User settings")
public class SettingsController {
    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PutMapping("/settings/introductions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setIntroductions(@Parameter(hidden = true) UserEntity user, SetIntroductorySettingsRequest request) {
        settingsService.setIntroductorySettings(user, request.getFirstName(), request.getLastName(), request.getBirthday());
    }

    @GetMapping("/settings/introductions")
    @ResponseStatus(HttpStatus.OK)
    public GetIntroductorySettingsResponse getIntroductions(@Parameter(hidden = true) UserEntity user) {
        return new GetIntroductorySettingsResponse(settingsService.getIntroductorySettings(user));
    }
}
