package com.tuling.springcloud.orders.anno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class DdSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("fsdfsdfsdfsdfsdfsd+++++++++++asdasdasd---------------");
        return new String[0];
    }
}
