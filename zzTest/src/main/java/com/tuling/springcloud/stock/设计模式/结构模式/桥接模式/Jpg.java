package com.tuling.springcloud.stock.设计模式.结构模式.桥接模式;

public class Jpg implements FileType{
    @Override
    public String img() {
        return "Jpg";
    }
}
