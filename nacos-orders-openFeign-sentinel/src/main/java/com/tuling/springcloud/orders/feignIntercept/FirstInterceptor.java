package com.tuling.springcloud.orders.feignIntercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstInterceptor implements RequestInterceptor {
    public void apply(RequestTemplate requestTemplate) {
        log.info("这是第一个拦截器，这里吧uri的最后一个数字改成了2，会拿到鸡蛋");
    }
}
