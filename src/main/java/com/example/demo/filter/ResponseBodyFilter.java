package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;
//@Component
public class ResponseBodyFilter implements Filter {
    public  void doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) response;

        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(httpServletResponse);

        chain.doFilter(request, wrappedResponse);

        byte[] originalBodybytes = wrappedResponse.getContentAsByteArray();

        String originalBody = new String(originalBodybytes);

        String modyfiedBody =
                """
                        {
                           "originalResponse" : %s,
                           "appName" : Student Management System                
                        }
                        """.formatted(originalBody);
        wrappedResponse.resetBuffer();
        wrappedResponse.getWriter().write(modyfiedBody);
        wrappedResponse.copyBodyToResponse();

    }}
