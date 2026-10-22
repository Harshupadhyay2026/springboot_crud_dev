package com.example.demo.configurations;

import com.example.demo.interceptors.AuthenticationInterceptor;
import com.example.demo.interceptors.AuthorizationInterceptor;
import com.example.demo.interceptors.LogingInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public LogingInterceptors logingInterceptors;
    public AuthenticationInterceptor authenticationInterceptor;
    public AuthorizationInterceptor authorizationInterceptor;

    public WebConfig (LogingInterceptors logingInterceptors,
                      AuthenticationInterceptor authenticationInterceptor,
                      AuthorizationInterceptor authorizationInterceptor){

        this.logingInterceptors = logingInterceptors;
        this.authenticationInterceptor = authenticationInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors (InterceptorRegistry registry){

        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/**")           // esko authenticate krna h
                .excludePathPatterns("/api/auth/login","api/public/**")   //its means ye sb ko authenticate ni krna hh
                .order(1);

        registry.addInterceptor(logingInterceptors)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/admin/*")
                .order(3);

        registry.addInterceptor(authorizationInterceptor)
                .order(2);
    }

}
