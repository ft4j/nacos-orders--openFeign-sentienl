package com.tuling.springcloud.orders.Aop;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HuiduAnnotation {

}
