package com.example.aop.aop.filterAndIntercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyINtercept())
                .addPathPatterns("/dddd")        //添加拦截路径，两种参数List<String>和String ...
                .order(1);        //设置拦截器顺序，由小到大，默认0
    }
}
