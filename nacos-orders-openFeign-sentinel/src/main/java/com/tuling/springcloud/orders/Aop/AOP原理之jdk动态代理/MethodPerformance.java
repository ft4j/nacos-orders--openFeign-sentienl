package com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理;

public class MethodPerformance {
    private long begin;
    private long end;
    private String serviceMethod;
    public MethodPerformance(String serviceMethod){
        this.serviceMethod = serviceMethod;
        this.begin = System.currentTimeMillis();
    }


    public void print(){
        this.end = System.currentTimeMillis();
        long elapse = this.end - this.begin;
        System.out.println(serviceMethod+"花费了："+elapse+"ms");
    }
}
