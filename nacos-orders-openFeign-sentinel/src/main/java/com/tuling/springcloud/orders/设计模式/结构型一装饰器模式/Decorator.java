package com.tuling.springcloud.orders.设计模式.结构型一装饰器模式;

public class Decorator extends Component {
    /**
     * 抽象组件
     */
    Component component;
    public Decorator(Component component){
        this.component = component;
    }
    @Override
    public void execute() {
        component.execute();
    }
}
