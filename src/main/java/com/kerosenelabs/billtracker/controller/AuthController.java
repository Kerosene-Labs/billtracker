package com.kerosenelabs.billtracker.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.service.AuthService;

@Controller
public class AuthController {
    private AuthService userService;

    public AuthController(@Qualifier("supabaseUserService") AuthService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "pages/login";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(@RequestParam String email, @RequestParam String password, Model model)
            throws IOException, AuthException {
        try {
            userService.createUser(email, password);
        } catch (AuthException e) {
            model.addAttribute("error", e.getMessage());
            return "pages/signup";
        }
        return "redirect:/welcome/nextSteps";
    }

    @GetMapping("/welcome/nextSteps")
    public String getWelcomeNextSteps() {
        return "pages/welcomeNextSteps";
    }

    @GetMapping("/confirmToken")
    public String handleConfirmToken(@RequestParam String tokenHash, @RequestParam String redirectTo) {
        System.out.println("tokenHash: " + tokenHash);
        return "pages/login";
    }

}
