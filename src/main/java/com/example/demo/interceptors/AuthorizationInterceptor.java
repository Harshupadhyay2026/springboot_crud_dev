package com.example.demo.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        String userRole = request.getHeader("x-user-role");


        if (userRole != null && !userRole.equals("ADMIN")){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{ \n" +
                    "    \"message\" : \"you are not Authorized to perform this tassk\"\n" +
                    "     }");

            return false;
        }
        return true;
    }}


