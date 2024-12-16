package com.kerosenelabs.billtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kerosenelabs.billtracker.entity.UserEntity;

@Controller
public class ExpensesController {
    @GetMapping("/expenses")
    public String getExpenses(UserEntity user) {
        return "pages/expenses";
    }
}
