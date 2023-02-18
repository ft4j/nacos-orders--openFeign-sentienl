package com.tuling.springcloud.orders.feignIntercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecondInterceptor implements RequestInterceptor {
    public void apply(RequestTemplate requestTemplate) {
        log.info("这是第二个拦截器");
    }
}
