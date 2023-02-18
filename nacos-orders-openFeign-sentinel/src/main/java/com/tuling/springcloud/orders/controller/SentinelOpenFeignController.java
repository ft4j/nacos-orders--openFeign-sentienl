package com.tuling.springcloud.orders.controller;

import com.tuling.springcloud.orders.feignService.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinelOpenFeign")
public class SentinelOpenFeignController {

    @Autowired
    StockFeignService stockFeignService;

    @RequestMapping("/getStock")
    public String getStock(){
        String s = stockFeignService.declineStockSentinel();
        return s+"      xxxxxxxx返回了返回了";
    }


}
