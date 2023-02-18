package com.tuling.springcloud.orders.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequestMapping("/rongduan")
@RestController
public class Sentine熔断降级Controller {

    @RequestMapping("/slow")
    @SentinelResource(value="testSentinelQps",fallback = "slowFallBack",blockHandler = "slowBlockHandler")
    public String slow() throws InterruptedException {
        //TimeUnit.MICROSECONDS.sleep(50);
        int s = 1/0;
        return "正常访问慢调用";
    }
    public String slowBlockHandler(BlockException e){
        return "慢调用熔断降级";
    }
    public String slowFallBack(BlockException e){
        return "慢调用异常";
    }

}
