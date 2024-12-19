package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.request.CreateSessionRequest;
import com.kerosenelabs.billtracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public void createSession(HttpSession httpSession, CreateSessionRequest createSessionRequest) throws AuthException {
        UserEntity user = userService.getUserByEmail(createSessionRequest.getEmail());
        if (userService.doesPasswordMatch(createSessionRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid password");
        }
        userService.establishSession(httpSession, user);
    }
}
