package com.tuling.springcloud.stock.设计模式.行为模式.访问者模式;

import lombok.Data;

@Data
public class B全职员工 implements B员工 {

    private String name;
    private double weeklyWage;
    private int workTime;

    public B全职员工(String name,double weeklyWage,int workTime){
        this.name = name;
        this.weeklyWage = weeklyWage;
        this.workTime = workTime;
    }

    @Override
    public void accept(ADepartment aDepartment) {
        aDepartment.visit(this);
    }
}
