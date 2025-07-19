package com.tuling.springcloud.orders.springboot自动装配;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportRegister.class)
public @interface MyEnableAutoRegisterConfig {

}
