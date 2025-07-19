package com.tuling.springcloud.stock.spring.AOP.AopDemo;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoAnnonation {
}
