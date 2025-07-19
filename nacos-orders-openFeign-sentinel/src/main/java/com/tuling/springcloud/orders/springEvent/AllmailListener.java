package com.tuling.springcloud.orders.springEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 这是一个事件监听器
 */
@Component
public class AllmailListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event == null){
            return;
        }
        System.out.println("dddddddddddddd"+event);
    }
}
