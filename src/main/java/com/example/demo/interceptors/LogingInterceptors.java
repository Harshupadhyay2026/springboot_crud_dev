package com.example.demo.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LogingInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        System.out.println("Inccoming request ------->>>>>>>");

        System.out.println( "Http Methode "+ request.getMethod() );
        System.out.println( "Request URI: " + request.getRequestURI());
        System.out.println( "Request Parameter " + request.getQueryString());
        System.out.println( "Client IP  " + request.getRemoteAddr());
        System.out.println("Token Header " +request.getHeader("token"));

      //  HandlerMethod method= (HandlerMethod) handler;

     if (handler instanceof HandlerMethod method) {
         String controllername = method.getBeanType().getName();
        String methodename = method.getMethod().getName();

        System.out.println("controllername is" + controllername);
         System.out.println("metgode name is " + methodename);}
//
//          System.out.println("pre handle called");
//      }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        System.out.println("post handle called");

    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        System.out.println("Response status "+ response.getStatus());

    }
}


