package com.example.security;

import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedException implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied - You do not have permission to access this resource!");
    }

    
}
