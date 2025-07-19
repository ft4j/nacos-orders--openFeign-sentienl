package com.tuling.springcloud.stock.threadLocal.深入理解;

public class Meth1 {
    static ThreadLocal<String> tl = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            //给ThreadLocal设置一个默认值
            return "zzzz";
        }
    };
    static ThreadLocal<String> tx = new ThreadLocal<String>();
    public void dsdsd(){
        /**
         * Thread对象有一个ThreadLocalMap成员变量，ThreadLocal的set方法就是给Thread的ThreadLocalMap设置
         * ThreadLocalMap和hashMap类似，一个线程有多个Threadlocal对象时，他们都会存在Thread的ThreadLocalMap中
         * 看一下set方法。它给Thread对象的成员变量设置了一个ThreadLocalMap
         * 由于ThreadLocalMap是成员变量，所以通过ThreadLocal存的值必然是当前线程私有的
         * 根据set方法，可以发现，当前线程的每一个ThreadLocal在一个ThreadLocalMap都对应一个Entry
         *
         * 注意，当线程的生命周期大于弱引用的生命周期时，才可能发生内存泄漏
         */
        tl.set("12345");
        tl.set("第一个");
        tx.set("第二个Threadlocal");
    }
}
