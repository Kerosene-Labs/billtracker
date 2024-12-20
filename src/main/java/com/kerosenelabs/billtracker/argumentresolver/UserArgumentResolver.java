package com.kerosenelabs.billtracker.argumentresolver;

import java.util.UUID;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private AuthService authService;

    public UserArgumentResolver(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType() != null && parameter.getParameterType().equals(UserEntity.class))
                && (parameter.getParameterName() != null && parameter.getParameterName().equals("user"));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpSession session = webRequest.getNativeRequest(HttpServletRequest.class).getSession();
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            throw new AuthException("Please log in.");
        }
        return authService.getUserById(userId);
    }
}
