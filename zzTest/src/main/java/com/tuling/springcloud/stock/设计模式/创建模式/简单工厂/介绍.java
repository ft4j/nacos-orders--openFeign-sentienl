package com.tuling.springcloud.stock.设计模式.创建模式.简单工厂;

import org.junit.Test;

public class 介绍 {
    /**
     * 简单工厂模式
     * 创建一个接口，这个接口有多个实现类
     * 另外还有一个工厂类
     * 工厂类有一个静态方法，根据静态类的参数
     * 返回不同的实现类
     * 使用该方法不需要考虑对象是如何实例化的，只需要知道应该传入什么样的参数即可
     *
     *
     *
     * 问题：如果要添加新的产品，就需要修改工厂类
     */
    @Test
    public void dd(){
        Factory f = new Factory();
        product x = f.getProduct("x");
        x.say();
    }
}
