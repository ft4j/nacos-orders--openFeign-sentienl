package com.tuling.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping("ddd")
    public String ddd(){
        return "ddd";
    }

    @RequestMapping("ddd/ddd")
    public String ddddd(){
        return "ddd/ddd";
    }

    @RequestMapping("cc/ddd/ddd")
    public String cddddd(){
        return "cc/ddd/ddd";
    }

    @RequestMapping("zzz")
    public String zzz(){
        return "zzz";
    }
}
