package com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理;
//添加切面
public class PerformanceMonitor {

    private static ThreadLocal<MethodPerformance> tl = new ThreadLocal<MethodPerformance>();

    public static void begin(String method){
        System.out.println("begin monitor");
        MethodPerformance pm = new MethodPerformance(method);
        tl.set(pm);
    }

    public static void end(){
        System.out.println("end monitor!");
        MethodPerformance pm = tl.get();
        pm.print();
    }

}




