package com.kerosenelabs.billtracker.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.service.ConfirmationTokenService;
import com.kerosenelabs.billtracker.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public AuthController(UserService userService, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/login")
    public String getLogin(HttpSession httpSession) throws IOException {
        return "pages/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model,
            HttpSession httpSession)
            throws AuthException {
        try {
            UserEntity user = userService.getUserByEmailAndPassword(email, password);
            userService.establishSession(httpSession, user);
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "A user with those credentials could not be found.");
            return "pages/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(@RequestParam String email, @RequestParam String password, Model model)
            throws IOException {
        userService.createUser(email, password);
        return "redirect:/confirmAccount";

    }

    @GetMapping("/logout")
    public String getLogOut(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/confirm")
    public String getConfirm(@RequestParam String t) throws AuthException {
        confirmationTokenService.confirmUser(t);
        return "pages/confirm";
    }

}
