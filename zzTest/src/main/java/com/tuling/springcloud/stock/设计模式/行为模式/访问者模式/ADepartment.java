package com.tuling.springcloud.stock.设计模式.行为模式.访问者模式;

public interface ADepartment {
    void visit(B临时工 b临时工);
    void visit(B全职员工 b全职员工);
}
