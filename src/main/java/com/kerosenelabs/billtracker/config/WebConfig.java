package com.kerosenelabs.billtracker.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kerosenelabs.billtracker.argumentresolver.SupabaseCookiesArgumentResolver;
import com.kerosenelabs.billtracker.service.supabasesdk.SupabaseCookieService;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private SupabaseCookieService supabaseCookieService;

    public WebConfig(SupabaseCookieService supabaseCookieService) {
        this.supabaseCookieService = supabaseCookieService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SupabaseCookiesArgumentResolver(supabaseCookieService));
    }
}
