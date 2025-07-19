package com.tuling.springcloud.stock.设计模式.创建模式.简单工厂;

public class Factory {
    public product getProduct(String string){
        if("x".equals(string)){
            return new ProductX();
        }
        if("y".equals(string)){
            return new ProductY();
        }
        return null;
    }
}
