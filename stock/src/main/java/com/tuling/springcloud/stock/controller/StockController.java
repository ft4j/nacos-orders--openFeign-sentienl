package com.tuling.springcloud.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {

    @RequestMapping("declineStock")
    public String declineStock() throws InterruptedException {
//        Thread.sleep(8000000);
        System.out.println("库存-1");
        return "库存-1";
    }

}
