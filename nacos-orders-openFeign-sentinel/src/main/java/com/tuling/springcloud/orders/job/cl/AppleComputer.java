package com.tuling.springcloud.orders.job.cl;

public class AppleComputer implements Computer {
    @Override
    public void getComputer() {
        System.out.println("得到一个苹果电脑");
    }
}
