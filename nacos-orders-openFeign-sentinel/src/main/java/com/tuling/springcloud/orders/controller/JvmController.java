package com.tuling.springcloud.orders.controller;

import com.tuling.springcloud.orders.domain.Uu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JvmController {
    @RequestMapping("jvmTest")
    public String jvmTest() throws InterruptedException {
        String[] strings = new String[100000000];
        Thread.sleep(2000);
        System.gc();
        return "dd";
    }


    @RequestMapping("Uu")
    public String dd(Uu u){
        return u.getEmail();
    }

}
