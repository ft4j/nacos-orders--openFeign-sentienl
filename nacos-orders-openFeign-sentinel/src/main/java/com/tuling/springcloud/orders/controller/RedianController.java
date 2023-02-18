package com.tuling.springcloud.orders.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/redian")
@RestController
public class RedianController {

    @RequestMapping("/redian/{id}")
    @SentinelResource(value="redianshuju",blockHandler = "redianBlock",fallback = "redianFallback")
    public String redian(@PathVariable("id") Integer id){
        System.out.println("热点流控正常访问");
        return "热点流控正常访问";
    }

    public String redianBlock(@PathVariable("id") Integer id, BlockException e){
        System.out.println("热点流控被流控了");
        return "热点流控被流控了";
    }

    public String redianFallback(@PathVariable("id") Integer id, BlockException e){
        System.out.println("热点流控被流控了redianFallback");
        return "热点流控被流控了redianFallback";
    }



}
