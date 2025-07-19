package com.tuling.springcloud.stock.spring.ioc.循环依赖;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public Object getObject() throws BeansException {

        return null;
    }



}
