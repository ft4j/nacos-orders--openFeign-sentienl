package com.tuling.springcloud.orders.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys")
public class SystemController {
    @RequestMapping("sys")
    public String sys(){
        return "系统级别的sentinel";
    }
}
