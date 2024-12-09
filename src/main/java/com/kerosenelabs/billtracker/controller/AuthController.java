package com.kerosenelabs.billtracker.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.AuthCredentials;
import com.kerosenelabs.billtracker.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(@Qualifier("supabaseUserService") AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model,
            HttpSession httpSession)
            throws IOException {
        try {
            AuthCredentials credentials = authService.getCredentials(email, password);
            authService.establishSession(httpSession, credentials);
        } catch (AuthException e) {
            model.addAttribute("error", e.getMessage());
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
        try {
            authService.createUser(email, password);
        } catch (AuthException e) {
            model.addAttribute("error", e.getMessage());
            return "pages/signup";
        }
        return "pages/welcomeNextSteps";
    }

    @GetMapping("/confirm")
    public String handleConfirmToken(@RequestParam(name = "access_token") String accessToken,
            @RequestParam("refresh_token") String refreshToken, HttpServletResponse response) {
        return "redirect:/login";
    }

}
