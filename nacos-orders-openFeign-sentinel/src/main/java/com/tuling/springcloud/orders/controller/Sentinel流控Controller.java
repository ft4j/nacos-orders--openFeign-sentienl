package com.tuling.springcloud.orders.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tuling.springcloud.orders.domain.User;
import com.tuling.springcloud.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("sentinel")
public class Sentinel流控Controller {

    @Autowired
    UserService userService;

    @RequestMapping("qps")
    @SentinelResource(value="testSentinelQps",fallback = "qpsFallBack",blockHandler = "qpsBlockHandler")
    public String testSentinelQps() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "正常访问";
    }
    public String qpsBlockHandler(BlockException exception){//这里方法就要加上这个异常
        exception.printStackTrace();
        return "这是被流控的方法";
    }
    public String qpsFallBack(){
        return "这是出错的方法";
    }

    //不使用@SentinelResource注解 直接使用统一异常处理的方式
    @RequestMapping("/yichang")
    public String tongyiyichang(){
        System.out.println(System.currentTimeMillis());
        return "统一异常处理方式";
    }


    /**
     * 下面是流控中的关联流控 开始
     * 关联流控模式指的是再一个接口流量压力过大的时候，会让另一个接口被限流
     * 这里模拟的是，订单接口流量过大导致订单查询接口被限流的业务场景
     * -----------------------------------
     */
    @RequestMapping("/add")
    @SentinelResource("add")
    public String addOrder() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "我添加了订单";
    }
    @RequestMapping("/query")
    @SentinelResource("query")
    public String queryOrder(){
        return "我查询了订单";
    }
    /**
     * 上面是流控中的关联流控 结束
     * -----------------------------------
     */

    /**
     * 下面是流控中的关联流控 开始
     * 关联流控模式指的是再一个接口流量压力过大的时候，会让另一个接口被限流
     * 这里模拟的是，订单接口流量过大导致订单查询接口被限流的业务场景
     * -----------------------------------
     */
    @RequestMapping("/lianlu1")
    @SentinelResource("lianlu1")
    public User lianlu1() {
        return userService.getUser(new User("lianlu1","15"));
    }
    @RequestMapping("/lianlu2")
    @SentinelResource("lianlu2")
    public User lianlu2() {
        return userService.getUser(new User("lianlu2","88"));
    }
    @RequestMapping("/lianlu3")
    @SentinelResource("lianlu2")
    public User lianlu3() {
        return userService.getUser(new User("lianlu3","2222"));
    }
    /**
     * 上面是流控中的关联流控 结束
     * -----------------------------------
     */


}
