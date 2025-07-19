package com.tuling.springcloud.stock.设计模式.结构模式.外观模式;

import org.junit.Test;

/**
 * 外观模式  看起来有点像工厂模式，都是通过一个通用的类的进行操作
 * 工厂模式是通过一个类去获取对象
 * 外观模式是通过一个类是调用对象
 *
 * nbs-gateway-service就是一个外观模式的体现，nbs-gateway-service就是facade
 * 所有的接口调用通过的都是同一个接口，区分接口只是依靠接口的中的参数来区分
 *
 * DefaultMQProducer仅仅是RocketMQ暴露给用户使用的外观类，它内部持有一个生产者实现DefaultMQProducerImpl，它才是具体的执行者。
 */
public class 介绍和测试 {
    @Test
    public void waiguanCeshi(){
        Facade f = new Facade();
        f.method();
    }
}
