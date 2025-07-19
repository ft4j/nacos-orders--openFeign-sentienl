package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        /**
         * 这地方可以将D的对象放入beanFactory，并将这个bean的name设置为
         * @Autowired
         * @Qualifier（"dddd"）可以引入这个对象，如果Qualifier参数出错，则报错
         */
        RootBeanDefinition aDef = new RootBeanDefinition(D.class);
        registry.registerBeanDefinition("dddd", aDef);

    }

}
