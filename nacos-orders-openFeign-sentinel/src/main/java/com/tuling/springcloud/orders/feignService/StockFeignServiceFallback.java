package com.tuling.springcloud.orders.feignService;

import org.springframework.stereotype.Component;

@Component
public class StockFeignServiceFallback implements StockFeignService {
    public String declineStock() {
        return null;
    }

    public String declineStockSentinel() {
        System.out.println("此处的熔断降级方法");
        return "此处是熔断降级方法,接口坏了在这里降级";
    }
}
