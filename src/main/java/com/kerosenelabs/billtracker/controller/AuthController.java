package com.kerosenelabs.billtracker.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kerosenelabs.billtracker.service.UserService;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(@Qualifier("supabaseUserService") UserService userService) {
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
        return "redirect:/welcome";
    }

}
