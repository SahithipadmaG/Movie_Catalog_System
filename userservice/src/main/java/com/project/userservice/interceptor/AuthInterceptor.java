package com.project.userservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.project.userservice.session.UserSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserSession userSession;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Check if user is logged in
        if (!userSession.isLoggedIn()) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}