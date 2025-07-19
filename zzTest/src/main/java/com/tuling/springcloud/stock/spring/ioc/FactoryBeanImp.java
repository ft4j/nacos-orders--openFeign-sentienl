package com.tuling.springcloud.stock.spring.ioc;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Data
@Component
public class FactoryBeanImp implements FactoryBean<FactoryBeanStudent> {
    private String name;
    private String age;
    //factoryBeanStudent就是通过factoryBean去实现往容器中添加bean的
    @Override
    public FactoryBeanStudent getObject() throws Exception {
        FactoryBeanStudent fbs = new FactoryBeanStudent();
        fbs.setId("1314");
        fbs.setName("陆磊");
        return fbs;
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanStudent.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
