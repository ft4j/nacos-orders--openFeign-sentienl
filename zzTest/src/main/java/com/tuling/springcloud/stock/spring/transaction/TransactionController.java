package com.tuling.springcloud.stock.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    DataService dataService;

    @RequestMapping("/tran")
    public String tran(){
        dataService.insertMethod1();
        return "OK";
    }
}
