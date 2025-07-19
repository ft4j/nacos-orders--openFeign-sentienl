package com.tuling.springcloud.config;

import com.tuling.springcloud.intercept.ControIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControInterceptConfiguration implements WebMvcConfigurer {
    @Autowired
    private ControIntercept controIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controIntercept).addPathPatterns("/ddd");
    }
}
