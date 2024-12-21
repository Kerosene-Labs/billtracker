package com.kerosenelabs.billtracker.controller;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.request.CreateUserRequest;
import com.kerosenelabs.billtracker.model.response.ConfirmUserResponse;
import com.kerosenelabs.billtracker.model.response.CreateUserResponse;
import com.kerosenelabs.billtracker.service.ConfirmationTokenService;
import com.kerosenelabs.billtracker.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "User")
public class UserController {
    private final AuthService authService;
    private final ConfirmationTokenService confirmationTokenService;

    public UserController(AuthService authService, ConfirmationTokenService confirmationTokenService) {
        this.authService = authService;
        this.confirmationTokenService = confirmationTokenService;
    }


    @PostMapping("/user")
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

    @PutMapping("/user/confirm")
    @Operation(summary = "Confirm a new user")
    public ResponseEntity<ConfirmUserResponse> confirmUser(@RequestParam String token) throws AuthException {
        confirmationTokenService.confirmUser(token);
        return ResponseEntity.ok(new ConfirmUserResponse("Confirmed"));
    }
}
