package com.tuling.springcloud.stock.spring.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/**
 * 这玩意的实现是AbstractApplicationContext下的prepareBeanFactory中添加的ApplicationContextAwareProcessor
 * ApplicationContextAwareProcessor的invokeAwareInterfaces让aware接口起效
 * 实例化完成后，进行初始化时initializeBean调用的beanPostProcess的applyBeanPostProcessorsBeforeInitialization下
 *
 * ApplicationContextAware的注入时机会比@Autowired早一点
 */
@Component
public class MyAwareBean implements BeanFactoryAware, ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        // 获取环境信息
        Environment environment = applicationContext.getEnvironment();
        String propertyValue = environment.getProperty("JAVA_HOME");
        System.out.println("this is my "+propertyValue);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    //这个值在afterPropertiesSet方法中设置
    private String sssss;
    @Override
    public void afterPropertiesSet() throws Exception {
        this.sssss = "这个方法没有参数，这个类被实例化之后，在初始化时，调用init-method之后执行这个方法。" +
                "这里给sssss加了个具体值";
        System.out.println("这里给sssss加了个具体值");
        System.out.println(applicationContext);
    }


    public String getSssss() {
        return sssss;
    }

    public void setSssss(String sssss) {
        this.sssss = sssss;
    }

    void xxxxx(){
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
