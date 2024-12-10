package com.kerosenelabs.billtracker.interceptor;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.service.ConfirmationTokenService;
import com.kerosenelabs.billtracker.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ConfirmedUserInterceptor implements HandlerInterceptor {
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;

    public ConfirmedUserInterceptor(ConfirmationTokenService confirmationTokenService, UserService userService) {
        this.confirmationTokenService = confirmationTokenService;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        UUID userId = (UUID) request.getSession().getAttribute("userId");
        UserEntity user = userService.getUserById(userId);
        confirmationTokenService.isUserConfirmed(user);
        return true;
    }
}
