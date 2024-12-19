package com.kerosenelabs.billtracker.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.exception.UnconfirmedUserException;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(AuthException.class)
//    public String handleAuthException(AuthException e, RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("error", e.getMessage());
//        return "redirect:/login";
//    }

//    @ExceptionHandler(UnconfirmedUserException.class)
//    public String handleUnconfirmedUserException(UnconfirmedUserException e, RedirectAttributes redirectAttributes) {
//    }
}
