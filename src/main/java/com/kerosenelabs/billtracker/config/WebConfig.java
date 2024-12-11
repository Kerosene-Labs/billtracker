package com.kerosenelabs.billtracker.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kerosenelabs.billtracker.argumentresolver.UserArgumentResolver;
import com.kerosenelabs.billtracker.interceptor.ConfirmedUserInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private UserArgumentResolver userArgumentResolver;
    private ConfirmedUserInterceptor confirmedUserInterceptor;

    public WebConfig(UserArgumentResolver userArgumentResolver, ConfirmedUserInterceptor confirmedUserInterceptor) {
        this.userArgumentResolver = userArgumentResolver;
        this.confirmedUserInterceptor = confirmedUserInterceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(confirmedUserInterceptor)
                .addPathPatterns("/home/**"); // Apply to specific routes
    }
}