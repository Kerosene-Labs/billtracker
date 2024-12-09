package com.kerosenelabs.billtracker.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kerosenelabs.billtracker.exception.AuthException;
import com.kerosenelabs.billtracker.model.AuthCredentials;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthCredentialsArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType() != null && parameter.getParameterType().equals(AuthCredentials.class))
                && (parameter.getParameterName() != null && parameter.getParameterName().equals("authCredentials"));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpSession session = webRequest.getNativeRequest(HttpServletRequest.class).getSession();

        // Retrieve the session attribute name from the annotation or parameter name
        SessionAttribute sessionAttribute = parameter.getParameterAnnotation(SessionAttribute.class);

        // Retrieve and return the session attribute
        Object attribute = session.getAttribute("authCredentials");
        if (attribute == null) {
            throw new AuthException("Session cookie not set");
        }
        return AuthCredentials.fromJson((String) attribute);
    }
}
