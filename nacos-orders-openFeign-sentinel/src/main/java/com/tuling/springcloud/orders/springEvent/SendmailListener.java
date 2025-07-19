package com.tuling.springcloud.orders.springEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 这是一个事件监听器
 */
@Component
public class SendmailListener implements ApplicationListener<EmailEvent> {
    @Override
    public void onApplicationEvent(EmailEvent event) {
        if(event == null){
            return;
        }
        System.out.println(event.getContent());

    }
}
