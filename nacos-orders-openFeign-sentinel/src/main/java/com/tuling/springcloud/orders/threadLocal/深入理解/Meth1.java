package com.tuling.springcloud.orders.threadLocal.深入理解;

public class Meth1 {
    public void dsdsd(){
        /**
         * 看一下set方法。它给Thread对象的成员变量设置了一个ThreadLocalMap
         * 由于ThreadLocalMap是成员变量，所以通过ThreadLocal存的值必然是线程私有的
         */
        ThreadLocal<String> tl = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "zzzz";
            }
        };
    }
}
