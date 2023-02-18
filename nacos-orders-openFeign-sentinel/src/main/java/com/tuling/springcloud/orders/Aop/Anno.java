package com.tuling.springcloud.orders.Aop;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Anno {
    String value() default "123";
    String ddd() default "ss";
}
