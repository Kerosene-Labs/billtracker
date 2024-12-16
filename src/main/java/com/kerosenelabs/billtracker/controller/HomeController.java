package com.kerosenelabs.billtracker.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(UserEntity user, Model model) {
        // if the user has completed their introductory settings, hide that card on the
        // home screen
        if (user.getFirstName() != null) {
            model.addAttribute("showIntroduction", false);
        } else {
            model.addAttribute("showIntroduction", true);
        }
        return "pages/home";
    }

    @GetMapping("/confirmAccount")
    public String getConfirmAccount() {
        return "pages/welcomeNextSteps";
    }

    @PostMapping("/home")
    public String handleEditIntroductions(UserEntity user, @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam LocalDate birthday) {

        userService.setIntroductorySettings(user, firstName, lastName, birthday);
        return "redirect:/home";
    }

}
