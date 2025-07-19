package com.tuling.springcloud.stock.线程及线程安全.锁;

public class StaticMethodYcn {

    /**
     * 这个方法的synchronized会锁住整个类的synchronized代码块
     */
    public static synchronized void syn() {
        System.out.println("synchronized static 方法执行开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synchronized static 方法执行结束");
    }

    public synchronized void notSyn() {
        System.out.println("synchronized普通 方法执行开始！！");
    }

    public static synchronized void dddddd() {
        System.out.println("synchronized静态方法 方法执行开始！！");
    }
}
