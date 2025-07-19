package com.tuling.springcloud.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    List<String> products = new ArrayList<String>();
    @Value("${user.name}")
    String port;

    @Value("${user.name}")
    String userName;

    @Value("${server.port}")
    public void setPort(String port) {
        this.port = port;
    }
    @Autowired
    public Environment environment;
    @RequestMapping("/selectProduct/{index}")
    public String selectProduct(@PathVariable("index") Integer index) {
        products.add("飞机");
        products.add("大米");
        products.add("鸡蛋");
        String s = products.get(index);
        System.out.println(environment.getProperty("server.port"));
        return "库存-1:" + s + ":" + port;
    }


    @RequestMapping("configPort")
    public String dsdfsdf(){
        return userName;
    }

}
