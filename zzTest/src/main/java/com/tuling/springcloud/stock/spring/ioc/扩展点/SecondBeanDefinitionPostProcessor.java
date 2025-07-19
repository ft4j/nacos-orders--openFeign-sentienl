package com.tuling.springcloud.stock.spring.ioc.扩展点;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//这玩意可以修改MyBeanDefinitionPostProcessor在所有beanFactoryPostProcessor中的排列顺序
@Order(15)
public class SecondBeanDefinitionPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("SecondBeanDefinitionPostProcessor打印一下");
    }
}
