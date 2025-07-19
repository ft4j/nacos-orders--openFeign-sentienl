package com.tuling.springcloud.orders.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 正向代理和方向代理的区别
 * 正向代理：给客户端代理，比如VPN，将数据发给VPN，再由VPN转给国外服务器，然后数据原路返回
 * vpn给客户端返回数据
 * 反向代理：给服务器代理，比如nginx，将数据发送给nginx，再由nginx转发给服务器，然后原路返回
 * nginx给服务器分发数据
 */
@RestController
public class TestNginxController {
    @RequestMapping("nginx")
    public String ng(){
        return Thread.currentThread().getName();
    }
}
