package com.example.aop.aop.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.env.ConfigurableEnvironment;
//springboot启动时为什么发布事件？为了开发人员扩展。在
public class ApplicationStartingEventListener implements SpringApplicationRunListener {
    public ApplicationStartingEventListener(SpringApplication application, String[] args) {
        // 必须实现此构造方法
    }
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("开始事件被启动了");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("环境被准备好了");
    }
}
