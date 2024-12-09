package com.kerosenelabs.billtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kerosenelabs.billtracker.model.AuthCredentials;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getMethodName(AuthCredentials authCredentials) {
        return "pages/home";
    }

}
