package com.tuling.springcloud.stock.Aa;

public interface 测试接口默认方法 {
    public void dd();
    public void ddc();
    default void print(){
        System.out.println("dddd");
    }

}
