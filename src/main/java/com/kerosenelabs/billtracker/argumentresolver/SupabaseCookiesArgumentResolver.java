package com.kerosenelabs.billtracker.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kerosenelabs.billtracker.model.supabase.SupabaseCookies;
import com.kerosenelabs.billtracker.service.supabasesdk.SupabaseCookieService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class SupabaseCookiesArgumentResolver implements HandlerMethodArgumentResolver {
    private SupabaseCookieService supabaseCookieService;

    public SupabaseCookiesArgumentResolver(SupabaseCookieService supabaseCookieService) {
        this.supabaseCookieService = supabaseCookieService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return (parameter.getParameterName() != null)
                && (parameter.getParameterType() == SupabaseCookies.class
                        && parameter.getParameterName().equals("supabaseCookies"));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return new SupabaseCookies(
                supabaseCookieService.getCookie(webRequest.getNativeRequest(HttpServletRequest.class), "accessToken"),
                supabaseCookieService.getCookie(webRequest.getNativeRequest(HttpServletRequest.class), "refreshToken"));
    }

}
