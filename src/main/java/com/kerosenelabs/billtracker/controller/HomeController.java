package com.kerosenelabs.billtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kerosenelabs.billtracker.model.supabase.SupabaseCookies;


@Controller
public class HomeController {
    @GetMapping("/home")
    public String getMethodName(SupabaseCookies supabaseCookies) {
        return "pages/home";
    }

}
