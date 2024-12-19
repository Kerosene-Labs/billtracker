package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.request.CreateSessionRequest;
import com.kerosenelabs.billtracker.model.request.CreateUserRequest;
import com.kerosenelabs.billtracker.model.response.ConfirmUserResponse;
import com.kerosenelabs.billtracker.model.response.CreateUserResponse;
import com.kerosenelabs.billtracker.service.ConfirmationTokenService;
import com.kerosenelabs.billtracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public AuthController(UserService userService, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping("/auth/session")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSession(HttpSession httpSession, @RequestBody CreateSessionRequest createSessionRequest) throws AuthException {
        UserEntity user = userService.getUserByEmail(createSessionRequest.getEmail());
        if (userService.doesPasswordMatch(createSessionRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid password");
        }
        userService.establishSession(httpSession, user);
    }

    @PostMapping("/auth/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserEntity user = userService.createUser(createUserRequest.getEmail(), createUserRequest.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateUserResponse(
                        "Created new user. Please check for confirmation email.",
                        user.getId()
                ));
    }

    @PutMapping("/auth/confirm")
    public ResponseEntity<ConfirmUserResponse> confirmuser(@RequestParam String token) throws AuthException {
        confirmationTokenService.confirmUser(token);
        return ResponseEntity.ok(new ConfirmUserResponse("Confirmed"));
    }
}
