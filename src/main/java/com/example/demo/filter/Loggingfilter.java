package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class Loggingfilter implements Filter {

@Override
    public  void doFilter(ServletRequest request,
                         ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {

    long startTime = System.currentTimeMillis(); // getting time

    HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    HttpServletResponse httpServletResponse= (HttpServletResponse) response;


    String requestId = UUID.randomUUID().toString();
    httpServletResponse.setHeader("X - Request-Id",requestId); // getting request id

    //Request log
        System.out.println("Incoming Request " +
                httpServletRequest.getMethod() +
                httpServletRequest.getRequestURI()) ;

try {
    chain.doFilter(request, response);
}

finally {
    long duration = System.currentTimeMillis() - startTime;

    //RESPONSE STATUS LOG

    System.out.println("Response status " +
            httpServletResponse.getStatus());

    System.out.println("API response time " + duration);
}
    }
}
