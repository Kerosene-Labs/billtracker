package com.kerosenelabs.billtracker.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.AuthCredentials;
import com.kerosenelabs.billtracker.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(HttpSession httpSession) throws IOException {
        UUID userId = (UUID) httpSession.getAttribute("userId");
        if (userId != null) {
            return "redirect:/home";
        }
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
        return "pages/welcomeNextSteps";

    }

    @GetMapping("/confirm")
    public String handleConfirmToken(@RequestParam(name = "access_token") String accessToken,
            @RequestParam("refresh_token") String refreshToken, HttpServletResponse response) {
        return "redirect:/login";
    }

}
