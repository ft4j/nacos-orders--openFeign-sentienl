package com.tuling.springcloud.orders.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tuling.springcloud.orders.feignService.ZaFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("za")
public class ZaController {

    @Autowired
    public ZaFeignService zaFeignService;

    @RequestMapping("stock")
    @SentinelResource(value="stockkkkkk",fallback = "fal",blockHandler = "bol")
    public String stock(){
        return zaFeignService.gettt();
    }

    public String bol(){
        return "这是blockHandler流控方法";
    }

    public String fal(){
        return "这是fallback异常方法";
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RequestMapping("za")
    public String dddd(){
        String s = stringRedisTemplate.opsForValue().get("key1");
        return s;
    }

}
