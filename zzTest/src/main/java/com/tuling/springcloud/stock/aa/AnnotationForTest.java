package com.tuling.springcloud.stock.aa;


import java.lang.annotation.*;


@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationForTest {
    String value() default "haha";
    int intValue()default 1;
    int[] intValues()default {1};
}
