package com.tuling.springcloud.stock.spring.ioc.扩展点;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

import static org.springframework.core.env.StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME;

public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {
    public MyClassPathXmlApplicationContext(String... configLocations){
        super(configLocations);
    }

    /**
     * 这里扩展的是，校验环境参数中的key的value值不为null，这里就是username不可以为null
     */
    public void initPropertySources(){
        //设置
        System.out.println("校验环境参数中的，key：username。它的value不可以为空");
        getEnvironment().setRequiredProperties("username");
        //下面是添加SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME属性的代码
//        MutablePropertySources propertySources = getEnvironment().getPropertySources();
//        Properties properties = new Properties();
//        properties.put("name","张三");
//        propertySources.addLast(new PropertiesPropertySource(SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME ,
//                new Properties()));
    }

    /**
     * beanFactory扩展设置beanFactory的属性值，这地方可以自定义所有的beanFactory的参数，这里以
     * beanDefinition和循环依赖参数为例，设置不可以循环循环依赖，设置不允许修改beanDefinition
     * 这里设置了不可以循环依赖，不可以覆盖同名bean的BeanDefinition
     * @param beanFactory
     */
    public void customizeBeanFactory(DefaultListableBeanFactory beanFactory){
        //在使用replace-method或lookup-method时，需要它为true才行
        super.setAllowBeanDefinitionOverriding(false);
        //设置不允许循环依赖
        super.setAllowCircularReferences(false);
        //将修改完的beanFactory设置回去
        super.customizeBeanFactory(beanFactory);
    }




}
