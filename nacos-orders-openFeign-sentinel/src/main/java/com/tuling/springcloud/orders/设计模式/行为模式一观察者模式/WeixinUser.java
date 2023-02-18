package com.tuling.springcloud.orders.设计模式.行为模式一观察者模式;

public class WeixinUser implements Observer {
    private String name;
    public WeixinUser(String name){
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+" 得到消息:"+message);
    }
}
