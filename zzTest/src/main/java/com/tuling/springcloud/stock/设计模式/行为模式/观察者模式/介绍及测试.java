package com.tuling.springcloud.stock.设计模式.行为模式.观察者模式;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 将接口的实现全部放入一个list，然后在一个循环中一次性全部调起
 */
public class 介绍及测试 {
    @Test
    public void ddd(){
        List<Food> l = new ArrayList();
        l.add(new Bear());
        l.add(new Huanggua());
        l.add(new Tomato());
        for (Food food : l) {
            food.eaten();
        }
    }
}
