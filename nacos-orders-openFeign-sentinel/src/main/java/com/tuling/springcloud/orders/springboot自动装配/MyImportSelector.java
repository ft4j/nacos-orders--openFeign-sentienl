package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("zxzxczxczxczxczxczxcxzczxczxczxczxczxc--selector");
        return new String[]{"com.tuling.springcloud.orders.springboot自动装配.ImportBean"};
    }
}
