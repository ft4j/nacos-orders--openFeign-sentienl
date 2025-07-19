package com.tuling.springcloud.stock.设计模式.结构模式.代理模式;


import org.junit.Test;

/**
 * 将一个功能单一的方法，放在一个代理类的方法中，以实现整体功能的增强
 * 典型的案例就是AOP，可以给方法添加日志、事务等
 * 传统就是买房子，客户买房子只付钱，代理的中介就帮忙带看房，缴费，交税，办证，联系客户等等
 * 而客户和中介，都实现了买房子的接口
 */
public class 介绍及测试 {
    @Test
    public void testProxy(){
        房地产 kehu = new 客户();
        kehu.买房子();
        System.out.println("+---------------");
        房地产 f = new 中介(kehu);
        f.买房子();
    }
}
