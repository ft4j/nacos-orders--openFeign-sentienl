package com.example.aop.aop.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListenerAnno {
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("准备就绪事件被启动了");
    }
}