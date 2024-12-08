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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model)
            throws IOException {
        try {
            userService.getToken(email, password);
        } catch (AuthException e) {
            model.addAttribute("error", e.getMessage());
            return "pages/login";
        }
        return "pages/index";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(@RequestParam String email, @RequestParam String password, Model model)
            throws IOException {
        try {
            userService.createUser(email, password);
        } catch (AuthException e) {
            model.addAttribute("error", e.getMessage());
            return "pages/signup";
        }
        return "pages/welcomeNextSteps";
    }

    @GetMapping("/confirm")
    public String handleConfirmToken(@RequestParam(name = "access_token") String accessToken,
            @RequestParam("refresh_token") String refreshToken, HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);
        // TODO add expiration for cookies

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);
        return "redirect:/home";
    }

}
