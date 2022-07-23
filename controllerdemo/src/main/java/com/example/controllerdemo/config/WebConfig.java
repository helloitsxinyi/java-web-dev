package com.example.controllerdemo.config;

import com.example.controllerdemo.interceptor.ProcessingTimeLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    ProcessingTimeLogInterceptor ptimelogger;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ptimelogger);
    }
}
