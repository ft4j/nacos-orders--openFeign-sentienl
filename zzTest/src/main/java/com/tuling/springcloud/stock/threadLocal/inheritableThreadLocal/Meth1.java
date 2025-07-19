package com.tuling.springcloud.stock.threadLocal.inheritableThreadLocal;

public class Meth1 {

    //InheritableThreadLocal是ThreadLocal的子类，这里它重写了ThreadLocal的initialValue方法。
    private static InheritableThreadLocal<Integer> familyFortunes = new InheritableThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 50;
        }
    };
    public void dsdsd(){
        /**
         * 看一下set方法。它给Thread对象的成员变量设置了一个ThreadLocalMap
         * 由于ThreadLocalMap是成员变量，所以通过ThreadLocal存的值必然是线程私有的
         */
        ThreadLocal<String> tl = new ThreadLocal<String>();

        tl.set("舒服");
    }
}
