package com.tuling.springcloud.orders.springEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEventController {
    @Autowired
    SendmaillEventPublisher sendmaillEventPublisher;
    @RequestMapping("event")
    public void dddd(){
        sendmaillEventPublisher.sendMailAsync("大哥回家了进行曲","半路遇难","小老弟收");
    }
}
