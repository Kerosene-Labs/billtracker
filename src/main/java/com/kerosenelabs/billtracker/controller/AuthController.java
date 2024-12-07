package com.kerosenelabs.billtracker.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String handleSignUp(@RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        userService.createUser(firstName, lastName, email, password);
        return "redirect:/welcome/nextSteps";
    }

    @GetMapping("/welcome/nextSteps")
    public String getWelcomeNextSteps() {
        return "pages/welcomeNextSteps";
    }

    @GetMapping("/confirmToken")
    public String handleConfirmToken(@RequestParam String tokenHash, @RequestParam String redirectTo) {
        System.out.println(tokenHash);
        return "pages/login";
    }

}
