package com.tuling.springcloud.orders.controller;

import com.tuling.springcloud.orders.feignService.RedissonFeignClient;
import com.tuling.springcloud.orders.feignService.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedissonController {
    @Autowired
    RedissonFeignClient redissonFeignClient;
    @RequestMapping("testRedisson")
    public String testRedisson(){
        String redisson = redissonFeignClient.redisson();
        return redisson+"加点料";
    }

    @Autowired
    TestFeign testFeign;
    @RequestMapping("testFeign")
    public String testFeign(){
        String redisson = testFeign.redisson();
        return redisson+"加点料";
    }
}
