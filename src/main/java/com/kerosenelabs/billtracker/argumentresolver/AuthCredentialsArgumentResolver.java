package com.kerosenelabs.billtracker.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kerosenelabs.billtracker.model.AuthCredentials;
import com.kerosenelabs.billtracker.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthCredentialsArgumentResolver implements HandlerMethodArgumentResolver {
    private AuthService authService;

    public AuthCredentialsArgumentResolver(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType() != null && parameter.getParameterType().equals(AuthCredentials.class))
                && (parameter.getParameterName() != null && parameter.getParameterName().equals("authCredentials"));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpSession session = webRequest.getNativeRequest(HttpServletRequest.class).getSession();

        return authService.getCredentialsFromSession(session);
    }
}
