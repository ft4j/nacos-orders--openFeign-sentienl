package com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 懒汉模式的单例
 */
public class LazySingleton懒汉线程安全ReentrantLock {
    private LazySingleton懒汉线程安全ReentrantLock(){

    }
    private static volatile LazySingleton懒汉线程安全ReentrantLock lazySingleton = null;
    private static Lock l = new ReentrantLock();
    public static LazySingleton懒汉线程安全ReentrantLock getLazySingleton(){
        if(lazySingleton == null){
            l.lock();
            if(lazySingleton == null){
                lazySingleton = new LazySingleton懒汉线程安全ReentrantLock();
            }
            l.unlock();
        }
        return lazySingleton;
    }
}
