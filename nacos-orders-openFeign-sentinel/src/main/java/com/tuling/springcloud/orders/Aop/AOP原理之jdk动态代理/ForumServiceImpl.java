package com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理;

/**
 * 被代理的类
 */
public class ForumServiceImpl implements ForumService {
    @Override
    public void removeTopic(String topicId) {
        System.out.println("删除topic");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeForum(String forumId) {
        System.out.println("删除forumId");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
