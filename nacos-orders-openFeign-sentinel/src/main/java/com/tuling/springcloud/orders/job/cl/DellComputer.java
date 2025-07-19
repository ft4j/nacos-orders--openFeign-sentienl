package com.tuling.springcloud.orders.job.cl;

public class DellComputer implements Computer {
    @Override
    public void getComputer() {
        System.out.println("得到一个戴尔电脑");
    }
}
