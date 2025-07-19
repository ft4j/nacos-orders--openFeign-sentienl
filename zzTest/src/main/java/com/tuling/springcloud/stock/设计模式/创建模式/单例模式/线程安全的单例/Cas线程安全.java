package com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例;

import java.util.concurrent.atomic.AtomicReference;

public class Cas线程安全 {
    private static final AtomicReference<Cas线程安全> ar = new AtomicReference<Cas线程安全>();

    private Cas线程安全(){

    }

    public static Cas线程安全 getInstance(){
        while(true){
            Cas线程安全 cas = ar.get();
            if(cas == null){
                if(ar.compareAndSet(null,new Cas线程安全())){
                    return ar.get();
                }
            }else{
                return cas;
            }
        }
    }
}
