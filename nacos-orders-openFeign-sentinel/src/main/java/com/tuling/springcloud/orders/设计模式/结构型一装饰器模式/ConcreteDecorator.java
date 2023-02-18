package com.tuling.springcloud.orders.设计模式.结构型一装饰器模式;

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void bdfore(){
        System.out.println("前置操作");
    }

    public void after(){
        System.out.println("后置操作");
    }

    public void execute(){
        bdfore();
        component.execute();
        after();
    }
}
