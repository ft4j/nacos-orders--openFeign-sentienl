package com.tuling.springcloud.stock.设计模式.创建模式.原型模式;

import lombok.Data;

@Data
public class Sheep implements Cloneable {
    private String name;
    private Integer age;
    private String className;

    @Override
    public Sheep clone() throws CloneNotSupportedException {
        Sheep sheep = (Sheep)super.clone();
        return sheep;
    }
}
