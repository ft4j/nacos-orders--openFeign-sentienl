package com.tuling.springcloud.orders.设计模式.行为模式一观察者模式;

public interface Subject {
    /**
     * 增加订阅者
     * @param observer
     */
    void attach(Observer observer);
    /**
     * 删除订阅者
     * @param observer
     */
    void detahch(Observer observer);

    /**
     * 通知订阅者更新消息
     * @param message
     */
    void notify(String message);
}
