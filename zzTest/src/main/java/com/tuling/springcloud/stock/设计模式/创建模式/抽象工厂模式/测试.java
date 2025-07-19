package com.tuling.springcloud.stock.设计模式.创建模式.抽象工厂模式;

import org.junit.Test;

public class 测试 {
    @Test
    public void ddd(){
        AInterface电器厂 factory = new B海尔();
        Interface冰箱 冰箱 = factory.create冰箱();
        Interface洗衣机 洗衣机 = factory.create洗衣机();
        Interface热水器 热水器 = factory.create热水器();
        Interface电视 电视 = factory.create电视();
        冰箱.showIceBox();
        洗衣机.showWashingMachine();
        热水器.showHotWaterMachine();
        电视.showTv();
    }
}
