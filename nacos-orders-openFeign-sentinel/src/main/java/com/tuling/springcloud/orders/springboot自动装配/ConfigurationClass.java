package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
/**
 * 直接引入C.class或者引入MyImportBeanDefinitionRegister
 * 再在MyImportBeanDefinitionRegister中注册D.class都可以引入对应的bean
 */
@Import({C.class,MyImportBeanDefinitionRegister.class})
public class ConfigurationClass {
//    @Bean
//    public C c(){
//        return new C();
//    }

    @Bean
    @ConditionalOnBean(A.class)
    public F f(){
        return new F("张三F","15F");
    }
}
