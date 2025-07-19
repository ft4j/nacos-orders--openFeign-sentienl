package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.User;
import com.tuling.springcloud.orders.threadLocal.Class1;
import com.tuling.springcloud.orders.threadLocal.Class2;
import com.tuling.springcloud.orders.threadLocal.深入理解.Meth1;
import com.tuling.springcloud.orders.threadLocal.深入理解.Meth2;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ---------------------------------------
 * 注意注意：Thread对象引用的对象为ThreadLocalMap，ThreadLocalMap弱引用ThreadLocal
 * threadlocal在线程结束之后，可以调用remove方法手工防止内存泄漏
 * +++++++++++++++++++++++++++++++++++++++
 *
 * 如果使用的threadlocal是一个静态对象，那么threadlocal始终不会被回收，这个内存的占据就是设计方面的考虑了，不是内存泄露的问题
 * 如果使用的threadlocal是一个成员变量，那么threadlocal对象应该在本方法创建的线程池生成的线程中使用，这时候，threadlocal的防止
 * 内存泄漏的设计才会排上用场
 *
 * Thread如何引用ThreadLocal？
 * 1、直接在线程中使用ThreadLocal对象
 * 2、ThreadLocal对象被设值，Thread对象本身存储了一个ThreadLocalMap对象，ThreadLocalMap对象本身又是一个以ThreadLocal作为key的对象
 * 一个ThreadLocal一旦使用，被两次引用
 * 3、直接在线程中引用这个threadLocal，结果是线程结束，这个引用结束了
 * 4、在ThreadLocalMap中的引用，则要直到线程结束才能结束，这时候，如果使用threadLocal的方法结束，ThreadLocal应该被回收了
 * 此时线程本身男的ThreadlocalMap对象弱引用这个ThreadLocal对象，所以Entry的key被回收，可以避免部分的内存泄漏
 */
public class ThreadLocalTest {
    @Test
    public void fsdfsdf(){
        Class1 c1 = new Class1();
        c1.ddd();
        Class2 c2 = new Class2();
        c2.ddd();
    }


    @Test
    public void fsggg(){
        ThreadLocal<String> tl = new ThreadLocal();
        tl.set("ddddd");
        tl.set("cccc");
        Object o = tl.get();
        System.out.println(o);
    }


    @Test
    public void ThreadPoolTestThreadLocal() throws InterruptedException {
        ThreadLocal tl = new ThreadLocal();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Thread.sleep(5);
            if(i == 0){
                es.execute(()->{
                    tl.set("13131313");
                });
            }else{
                es.execute(()->{

                });
            }
        }
        for (int i = 0; i < 3000; i++) {
            Thread.sleep(1000);
            es.execute(()->{
                System.out.println(tl.get());
            });
        }

    }


    @Test
    public void 虚引用测试(){
        String str = new String("abc");
        ReferenceQueue queue = new ReferenceQueue();
        // 创建虚引用，要求必须与一个引用队列关联
        PhantomReference pr = new PhantomReference(str, queue);

        for (int i = 0; i < 50; i++) {
            System.gc();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Reference poll = queue.poll();
            if(poll != null){
                System.out.println("虚引用被回收了！");
            }
        }

    }

    @Test
    public void dddddd(){
        User u = new User();
        WeakReference<User> v = new WeakReference<User>(u);
        System.out.println(u);
        System.out.println(v);
        u = null;
        System.out.println(u);
        System.out.println(v);
    }

    @Test
    public void dfsdfsdf(){
        ThreadLocal t = new ThreadLocal();
        t.set("dd");

        Object o = t.get();
        System.out.println(o);
    }


    @Test
    public void dddddddd(){
        ThreadLocal<String> tl = new ThreadLocal<String>();
        tl.set("ddd");
    }

    /**
     * 模拟ThreadLocal的多线程，同一个key获得不同value案例
     */
    @Test
    public void sadssd(){

        String ss = new String("dddd");
        Thread d = new Thread(()->{
            Map m = new HashMap<String,String>();
            m.put(ss,"线程1");
            System.out.println(m.get(ss));
        });
        Thread d1 = new Thread(()->{
            Map m = new HashMap<String,String>();
            m.put(ss,"线程2");
            System.out.println(m.get(ss));
        });

        d.start();
        d1.start();

    }

    @Test
    public void ffgfg(){
        Meth1 m1 = new Meth1();
        m1.dsdsd();
        Meth2 m2 = new Meth2();
        m2.dfsdf();
    }


    @Test
    public void sdsd() throws IOException {
        FileInputStream fis = new FileInputStream("");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bf = new BufferedReader(isr);
        bf.readLine();
    }
}
