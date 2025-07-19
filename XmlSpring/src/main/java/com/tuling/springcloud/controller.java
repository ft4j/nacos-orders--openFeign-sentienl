package com.tuling.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @Autowired
    Environment environment;
    @RequestMapping("one")
    public String ddd(){
        return "one"+Integer.parseInt(environment.getProperty("server.port", "8080"));
    }

    @RequestMapping("demo/one")
    public String dddd(){
        return "demo/one"+Integer.parseInt(environment.getProperty("server.port", "8080"));
    }
}
