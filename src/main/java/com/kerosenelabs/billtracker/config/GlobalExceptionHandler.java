package com.kerosenelabs.billtracker.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kerosenelabs.billtracker.exception.AuthException;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public String handleAuthException(AuthException e, HttpSession httpSession) {
        httpSession.set();
        return "pages/login";
    }
}
