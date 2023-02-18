package com.tuling.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "nacos-stock", path = "/stock",fallback = StockFeignServiceFallback.class)
public interface StockFeignService {
    @RequestMapping("/declineStock")
    public String declineStock();
    //这用来测试流控降级规则
    @RequestMapping("/declineStockSentinel")
    public String declineStockSentinel();
}
