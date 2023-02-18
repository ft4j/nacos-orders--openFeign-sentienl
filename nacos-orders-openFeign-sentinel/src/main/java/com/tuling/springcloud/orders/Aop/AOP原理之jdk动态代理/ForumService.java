package com.tuling.springcloud.orders.Aop.AOP原理之jdk动态代理;

/**
 * 被代理接口
 */
public interface ForumService {
    void removeTopic(String topicId);
    void removeForum(String forumId);
}
