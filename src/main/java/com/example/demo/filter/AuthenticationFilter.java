package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

 @Component
 @Order(1)
public class AuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest =
                (HttpServletRequest)request;
        HttpServletResponse httpServletResponse=
                (HttpServletResponse) response;

       String token = httpServletRequest.getHeader("token");
        String apiKey = httpServletRequest.getHeader("x-api-key");

       if (token ==null || !token.equals("12345")){
  httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  return;}
       if (apiKey ==null || !apiKey.equals("secret123")){
           httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

           httpServletResponse.setContentType("application/json");
           httpServletResponse.getWriter().write(

                  "{\n" +
                       "   \"message\" : \"api key is inavalid or missing\"\n" +
                   "]"

           );
           return;

       }

       chain.doFilter(request,response);



    }
}
