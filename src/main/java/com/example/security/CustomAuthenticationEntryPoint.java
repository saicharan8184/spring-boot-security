package com.example.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authEx) throws IOException, ServletException {
        // res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // res.getWriter().write("Unauthorized - please login!");
        res.sendRedirect("/login?error=true");
    }
    
}
