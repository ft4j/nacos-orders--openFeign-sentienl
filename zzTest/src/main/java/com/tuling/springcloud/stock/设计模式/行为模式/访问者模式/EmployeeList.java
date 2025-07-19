package com.tuling.springcloud.stock.设计模式.行为模式.访问者模式;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    List<B员工> l = new ArrayList();

    public void addEmployee(B员工 b员工){
        l.add(b员工);
    }

    public void userEmployee(ADepartment aDepartment){
        for (B员工 b员工 : l) {
            b员工.accept(aDepartment);
        }
    }
}
