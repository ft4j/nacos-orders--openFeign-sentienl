package com.tuling.springcloud.stock.设计模式.结构模式.适配器模式;

import org.junit.Test;

/**
 * spring的接口调用repository方法时，如果controller的参数和repository接口不一致时
 * 我们就需要一个service作为一个适配器去转换参数，从而实现controller和repository的适配
 * 以实现功能的复用，此时 controller 和 repository都是已存在的
 *
 * 适配器模式就是最接近日常写代码的模式，相信每一个程序员都是这么干的，记得 Service implements Repository
 *
 * 在spring源码中，为了让beanFactory可以读取到bean信息，beanFactory会被作为参数包装到一个BeanDefinitionReader中，
 * 通过BeanDefinitionReader，获取各种bean的定义信息，比如读取xml配置的springbean信息，
 * 可以通过xmlBeanDefinitionReader获取beanDefinition;
 *
 */
public class 介绍及测试 {
    @Test
    public void tes(){
        /**
         * adapter实现了接口的调用，并复用了repository的方法
         * queryUserName
         */
        Adapter adapter = new Adapter();
        adapter.getUserName("2");
    }
}
