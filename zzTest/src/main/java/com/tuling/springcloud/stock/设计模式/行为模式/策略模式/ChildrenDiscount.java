package com.tuling.springcloud.stock.设计模式.行为模式.策略模式;

public class ChildrenDiscount implements Discount{
    @Override
    public double calculate(double price) {
        return price - 10 ;
    }
}
