package com.tuling.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name  = "nacos-product1")
public interface TestFeign {
    @RequestMapping("/product//selectProduct/2")
    String redisson();
}
