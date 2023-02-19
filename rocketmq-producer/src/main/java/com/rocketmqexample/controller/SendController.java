package com.rocketmqexample.controller;

import com.rocketmqexample.rocket.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    @Autowired
    ProducerService producerService;
    @RequestMapping("/send")
    public String senddd(){
        producerService.sendSimpleMessage();
        return "ok";
    }

    @RequestMapping("/sendSync")
    public String sendSync(){
        producerService.sendSyncMessage();
        return "ok";
    }

    @RequestMapping("/sendASync")
    public String sendASync(){
        producerService.sendAsyncMessage();
        return "ok";
    }

    @RequestMapping("/orderly")
    public String sendOneWayOrderly(){
        producerService.sendOrderly();
        return "ok";
    }

    @RequestMapping("/transaction")
    public String sendTransaction(){
        producerService.transactionMessage();
        return "ok";
    }

    @RequestMapping("/发送普通消息")
    public String 发送普通消息(){
        producerService.发送普通消息();
        return "OK";
    }

    @RequestMapping("sendOneMsg")
    public String sendOneMsg(){
        String s = producerService.sendOneMsg();
        return s;
    }




}
