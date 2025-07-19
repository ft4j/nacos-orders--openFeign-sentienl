package com.tuling.springcloud.stock.设计模式.行为模式.访问者模式;

public interface B员工 {
    //员工接受了部门的访问，有了这个方法，访问者就可以访问被访问者了
    void accept(ADepartment aDepartment);
}
