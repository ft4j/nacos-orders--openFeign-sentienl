package com.tuling.springcloud.stock.设计模式.结构模式.桥接模式;

public class Windows implements Operator {
    @Override
    public void showImg(FileType fileType) {
        System.out.println("在Windows下展示了："+fileType.img());
    }
}
