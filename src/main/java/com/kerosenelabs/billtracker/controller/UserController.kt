package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
