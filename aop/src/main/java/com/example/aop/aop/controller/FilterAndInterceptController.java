package com.example.aop.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterAndInterceptController {

    @RequestMapping("/dddd")
    public String dddd(){
        System.out.println("controller被执行了");
        return "ccc";
    }
}
