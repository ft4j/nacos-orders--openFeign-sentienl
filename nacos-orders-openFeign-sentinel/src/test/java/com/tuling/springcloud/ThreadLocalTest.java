package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.User;
import com.tuling.springcloud.orders.threadLocal.Class1;
import com.tuling.springcloud.orders.threadLocal.Class2;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ThreadLocalTest {
    @Test
    public void fsdfsdf(){
        Class1 c1 = new Class1();
        c1.ddd();
        Class2 c2 = new Class2();
        c2.ddd();
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
}
