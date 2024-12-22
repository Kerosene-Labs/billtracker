package com.kerosenelabs.billtracker.argumentresolver;

import java.util.UUID;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.OAuth2Provider;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kerosenelabs.billtracker.entity.UserEntity;
import com.kerosenelabs.billtracker.service.UserService;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private UserService userService;

    public UserArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType() != null && parameter.getParameterType().equals(UserEntity.class))
                && (parameter.getParameterName() != null && parameter.getParameterName().equals("user"));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // parse auth jwt
        String authHeader = webRequest.getHeader("Authorization");
        if (authHeader == null) {
            throw new AuthException("Authorization header is required");
        }
        String userId = userService.getIdFromJwt(authHeader);
        OAuth2Provider provider = userService.getProviderFromJwt(authHeader);
        return userService.getUserBySubAndProvider(userId, provider);
    }
}
