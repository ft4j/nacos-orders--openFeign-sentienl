package com.tuling.springcloud.stock.spring.ioc.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * MyBeanDefinitionPostProcessor的扩展bean
 */
@Data
@Component
public class 扩展bean {
    //这个字段通过占位符赋值
    private String itsName;
    //这个字段通过MyBeanFactoryPostProcessor赋值
    private String crazyPostProcessor;

    @Override
    public String toString() {
        return "扩展bean{" +
                "itsName='" + itsName + '\'' +
                ", crazyPostProcessor='" + crazyPostProcessor + '\'' +
                '}';
    }
}
