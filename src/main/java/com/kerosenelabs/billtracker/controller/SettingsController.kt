package com.kerosenelabs.billtracker.controller

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.model.request.SetIntroductorySettingsRequest
import com.kerosenelabs.billtracker.model.response.GetIntroductorySettingsResponse
import com.kerosenelabs.billtracker.service.SettingsService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Settings", description = "User settings")
class SettingsController(private val settingsService: SettingsService) {
    @PutMapping(value = ["/settings/introductions"], consumes = ["application/json"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun setIntroductions(
        @Parameter(hidden = true) user: UserEntity,
        @RequestBody request: SetIntroductorySettingsRequest
    ) {
        settingsService.setIntroductorySettings(user, request.firstName, request.lastName, request.birthday)
    }

    @GetMapping("/settings/introductions")
    @ResponseStatus(HttpStatus.OK)
    fun getIntroductions(@Parameter(hidden = true) user: UserEntity): GetIntroductorySettingsResponse {
        return GetIntroductorySettingsResponse(settingsService.getIntroductorySettings(user))
    }
}
