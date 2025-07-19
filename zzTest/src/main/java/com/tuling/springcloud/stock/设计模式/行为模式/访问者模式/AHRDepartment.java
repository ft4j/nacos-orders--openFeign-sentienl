package com.tuling.springcloud.stock.设计模式.行为模式.访问者模式;
//HR部门
public class AHRDepartment implements ADepartment {

    @Override
    public void visit(B临时工 b临时工) {
        System.out.println("财务部门访问了临时工："+b临时工.getName());
    }

    @Override
    public void visit(B全职员工 b全职员工) {
        System.out.println("财务部门访问了全职员工："+b全职员工.getName());
    }
}
