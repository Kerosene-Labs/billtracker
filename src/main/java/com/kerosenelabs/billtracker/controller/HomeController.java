package com.kerosenelabs.billtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kerosenelabs.billtracker.entity.UserEntity;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome(UserEntity user) {
        return "pages/home";
    }

}
