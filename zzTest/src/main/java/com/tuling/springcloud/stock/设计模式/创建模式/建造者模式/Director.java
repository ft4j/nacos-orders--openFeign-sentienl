package com.tuling.springcloud.stock.设计模式.创建模式.建造者模式;

public class Director {
    private AbstractBuilder abstractBuilder;
    //构造器
    public Director(AbstractBuilder abstractBuilder){
        this.abstractBuilder = abstractBuilder;
    }
    //set方法   构造器和set方法都可以设值具体的Builder
    public void setAbstractBuilder(AbstractBuilder abstractBuilder){
        this.abstractBuilder = abstractBuilder;
    }

    //构建产品的具体方法
    public BuildedProduct construct(){
        abstractBuilder.buildPartA();
        abstractBuilder.buildPartB();
        abstractBuilder.buildPartC();
        return abstractBuilder.getResult();
    }


}
