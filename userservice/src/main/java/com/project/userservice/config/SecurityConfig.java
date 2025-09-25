package com.project.userservice.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.userservice.interceptor.AuthInterceptor;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    
    public SecurityConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/register", "/logout", "/error");
    }
}
