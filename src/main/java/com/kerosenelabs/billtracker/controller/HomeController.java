package com.kerosenelabs.billtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getMethodName(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {

        }
        return "redirect:/";
    }

}
