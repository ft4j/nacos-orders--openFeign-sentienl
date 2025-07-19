package com.tuling.springcloud.orders.springEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SendmaillEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void sendMailAsync(String subject, String content, String email) {
        publisher.publishEvent(new EmailEvent(this,subject, content, email));

    }
}
