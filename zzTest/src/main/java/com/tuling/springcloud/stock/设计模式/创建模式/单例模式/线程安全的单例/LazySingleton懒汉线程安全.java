package com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例;

/**
 * 懒汉模式的单例
 */
public class LazySingleton懒汉线程安全 {
    private LazySingleton懒汉线程安全(){

    }
    private static volatile LazySingleton懒汉线程安全 lazySingleton = null;

    public static LazySingleton懒汉线程安全 getLazySingleton(){
        if(lazySingleton == null){
            synchronized (LazySingleton懒汉线程安全.class){
                if(lazySingleton == null){
                    lazySingleton = new LazySingleton懒汉线程安全();
                }
            }
        }
        return lazySingleton;
    }

}
