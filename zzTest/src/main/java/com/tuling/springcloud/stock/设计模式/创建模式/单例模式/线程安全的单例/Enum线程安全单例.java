package com.tuling.springcloud.stock.设计模式.创建模式.单例模式.线程安全的单例;

public enum Enum线程安全单例 {
    /**
     * emun翻编译一下，它的变量全都是static final的，会在类加载的时候就完成初始化，天然就是线程安全的
     * enum天然就会实例化自身，
     */
    INSTANCE;
    public void enumMethod(){
        System.out.println("Enum线程安全单例");
    }
}
