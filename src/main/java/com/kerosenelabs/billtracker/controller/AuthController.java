package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.request.CreateSessionRequest;
import com.kerosenelabs.billtracker.model.request.CreateUserRequest;
import com.kerosenelabs.billtracker.model.response.ConfirmUserResponse;
import com.kerosenelabs.billtracker.model.response.CreateUserResponse;
import com.kerosenelabs.billtracker.model.response.ValidateSessionResponse;
import com.kerosenelabs.billtracker.service.ConfirmationTokenService;
import com.kerosenelabs.billtracker.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Auth", description = "User and auth")
public class AuthController {
    private final AuthService authService;
    private final ConfirmationTokenService confirmationTokenService;

    public AuthController(AuthService authService, ConfirmationTokenService confirmationTokenService) {
        this.authService = authService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping("/auth/session")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Establish a new session for a given user")
    public void createSession(HttpSession httpSession, @RequestBody CreateSessionRequest createSessionRequest) throws AuthException {
        UserEntity user = authService.getUserByEmail(createSessionRequest.getEmail());
        if (!authService.doesPasswordMatch(createSessionRequest.getPassword(), user.getPassword())) {
            throw new AuthException("A user with those credentials could not be found");
        }
        authService.establishSession(httpSession, user);
    }

    @GetMapping("/auth/session/validate")
    @Operation(summary = "Validate a session, ensuring it is still valid")
    public ResponseEntity<ValidateSessionResponse> validateSession(HttpSession httpSession) {
        return ResponseEntity.ok(new ValidateSessionResponse(httpSession == null || httpSession.isNew()));
    }

    @PostMapping("/auth/user")
    @Operation(summary = "Create a new user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserEntity user = authService.createUser(createUserRequest.getEmail(), createUserRequest.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateUserResponse(
                        "Created new user. Please check for confirmation email.",
                        user.getId()
                ));
    }

    @PutMapping("/auth/confirm")
    @Operation(summary = "Confirm a new user")
    public ResponseEntity<ConfirmUserResponse> confirmuser(@RequestParam String token) throws AuthException {
        confirmationTokenService.confirmUser(token);
        return ResponseEntity.ok(new ConfirmUserResponse("Confirmed"));
    }

    @PostMapping("/auth/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Log out")
    public void logout(HttpSession httpSession) {
        authService.endSession(httpSession);
    }
}
