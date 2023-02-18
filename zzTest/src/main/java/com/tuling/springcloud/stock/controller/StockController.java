package com.tuling.springcloud.stock.controller;

import com.tuling.springcloud.stock.aop.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {

    @RequestMapping("declineStock")
    public String declineStock() {
        System.out.println("库存-1");
        return "库存-1";
    }
    @Autowired
    IHelloWorldService iHelloWorldService;
    @RequestMapping("aop")
    public String aop() {
        iHelloWorldService.sayHello();
        return "说完了";
    }

}
