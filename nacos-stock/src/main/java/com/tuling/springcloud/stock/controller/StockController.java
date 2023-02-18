package com.tuling.springcloud.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    String port;

    @Value("${server.port}")
    public void setPort(String port) {
        this.port = port;
    }

    @RequestMapping("/declineStock")
    public String declineStock() {
        System.out.println("库存-1");
        return "库存-1" + port;
    }


    @RequestMapping("/declineStockSentinel")
    public String declineStockSentinel() {
        System.out.println("库存-1");
        return "库存-1" + port;
    }

}
