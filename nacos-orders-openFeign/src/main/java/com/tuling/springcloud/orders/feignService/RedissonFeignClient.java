package com.tuling.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name  = "nacos-orders-openFeign-sentinel")
public interface RedissonFeignClient {
    @RequestMapping("redissonLockTimeOutTest")
    String redisson();
}
