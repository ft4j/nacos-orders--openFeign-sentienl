package com.tuling.springcloud.stock.设计模式.创建模式.单例模式;

/**
 * 懒汉模式的单例
 */
public class LazySingleton {
    private LazySingleton(){

    }
    /**
     * 为什么要volatile?为了保证可见性，可以在LazySingleton被初始化时，让其他线程直接读取到主内存中的lazySingleton对象
     * 不会说多线程下判断失准
     */
    private static volatile LazySingleton lazySingleton = null;

    public static LazySingleton getLazySingleton(){
        if(lazySingleton == null){
            synchronized (LazySingleton.class){
                if(lazySingleton == null){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

}
