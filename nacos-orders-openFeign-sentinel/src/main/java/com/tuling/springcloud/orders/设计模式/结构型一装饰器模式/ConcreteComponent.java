package com.tuling.springcloud.orders.设计模式.结构型一装饰器模式;

public class ConcreteComponent extends Component {
    @Override
    public void execute() {
        System.out.println("这是被装饰对象，也是具体的实现方法");
    }
}
