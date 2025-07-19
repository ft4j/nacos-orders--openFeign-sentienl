package com.tuling.springcloud.stock.线程及线程安全.JUC;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class 原子类 {
    AtomicInteger ai = new AtomicInteger(0);
    @Test
    public void dfdfd() throws InterruptedException {

        Thread d = new Thread(()->{
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ai.getAndIncrement();
            }
        });
        Thread d1 = new Thread(()->{
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ai.getAndIncrement();
            }
        });
        d.start();
        d1.start();
        Thread.sleep(2200);
        System.out.println(ai.get());
        //在获取之后+1，但是结果不一定是原来的值，可能在这个过程中，别的线程也会操作，结果是不可控的
        ai.getAndIncrement();
        //+1之后在获取，但是结果不一定是原来的值+1，可能在这个过程中，别的线程也会操作，结果是不可控的
        ai.addAndGet(1);
    }


    AtomicReference<TestRef> ar = new AtomicReference<TestRef>();
    Integer threadSuccess1 = 0;
    Integer threadSuccess2 = 0;

    /**
     * 注意ar.compareAndSet(testRef,tr1)不会必然成功的，失败，张三的年龄就不会+1
     * 要注意后续，成功和失败后分别执行什么代码
     * 但是atomicInteger的ai.getAndIncrement();则必然会+1，因为它内存做了循环
     * @throws InterruptedException
     */
    @Test
    public void testAtomicReference() throws InterruptedException {
        TestRef tr = new TestRef();
        tr.setAge(0);
        tr.setName("张三");
        tr.setClassName("初三");
        ar.set(tr);

        Thread d1 = new Thread(()->{
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestRef tr1 = new TestRef();
                TestRef testRef = ar.get();
                tr1.setAge(testRef.getAge()+1);
                tr1.setName(testRef.getName());
                tr1.setClassName(testRef.getClassName());
                if(ar.compareAndSet(testRef,tr1)){
                    threadSuccess1++;
                }
            }
        });
        Thread d2 = new Thread(()->{
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestRef tr1 = new TestRef();
                TestRef testRef = ar.get();
                tr1.setAge(testRef.getAge()+1);
                tr1.setName(testRef.getName());
                tr1.setClassName(testRef.getClassName());
                if(ar.compareAndSet(testRef,tr1)){
                    threadSuccess2++;
                }
            }
        });
        d1.start();
        d2.start();
        Thread.sleep(4000);
        System.out.println("Thread1成功次数："+threadSuccess1);
        System.out.println("Thread2成功次数："+threadSuccess2);
        Integer age = ar.get().getAge();
        System.out.println("它几岁了："+age);
    }


    @Test
    public void dsdsdsd() throws InterruptedException {
        LinkedBlockingDeque l = new LinkedBlockingDeque(2);
        Thread d = new Thread(()->{
            try {
                for (int i = 0; i < 4; i++) {
                    l.push(i+"");
                    System.out.println("Thread1添加了数据："+i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        d.start();

        Thread.sleep(2000);
        Object take = l.take();
        System.out.println(take+"   这是take到的第一个数据");
        Thread.sleep(500);
        Object take1 = l.take();
        System.out.println(take1+"   这是take到的第二个数据");
        Thread.sleep(500);
        Object take2 = l.take();
        System.out.println(take2+"   这是take到的第三个数据");
        Thread.sleep(500);
        Object take3 = l.take();
        System.out.println(take3+"   这是take到的第四个数据");


        Thread.sleep(500);
        Object take4 = l.take();
        System.out.println(take4+"   这是take到的第五个数据");
        Thread.sleep(10000);

    }

    @Test
    public void dsdsffff() throws InterruptedException {
        LinkedBlockingDeque l = new LinkedBlockingDeque(3);
        l.put("1");
        l.put("2");
        l.put("3");
        System.out.println(l);

        LinkedBlockingDeque l1 = new LinkedBlockingDeque(3);
        l1.push("1");
        l1.push("2");
        l1.push("3");
        System.out.println(l1);

        LinkedBlockingDeque l2 = new LinkedBlockingDeque(3);
        l2.add("1");
        l2.add("2");
        l2.add("3");
        System.out.println(l2);

        LinkedBlockingDeque l3 = new LinkedBlockingDeque(3);
        l3.offer("1");
        l3.offer("2");
        l3.offer("3");
        System.out.println(l3);
    }

    @Test
    public void dssgrerwer() throws InterruptedException {
        LinkedBlockingDeque l = new LinkedBlockingDeque(4);
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        System.out.println(l);

        System.out.println(l.poll());
        System.out.println(l);
        System.out.println(l.poll());
        System.out.println(l);

        LinkedBlockingDeque l1 = new LinkedBlockingDeque(4);
        l1.put("1");
        l1.put("2");
        l1.put("3");
        l1.put("4");
        System.out.println(l1);

        System.out.println(l1.take());
        System.out.println(l1);
        System.out.println(l1.take());
        System.out.println(l1);

    }

    @Test
    public void dsdsdzzzsd(){

    }

}
