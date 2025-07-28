package com.tuling.springcloud.stock.aa;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Ccc {
    @Test
    @Transactional
    public void dd() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class c = Class.forName("com.tuling.springcloud.stock.aa.Ccccccc");
        Object o = c.newInstance();
        Annotation annotation = c.getAnnotation(AnnotationForTest.class);
        System.out.println(annotation);
        Constructor[] constructors = c.getConstructors();
        System.out.println(constructors);
        Method declaredMethod = c.getDeclaredMethod("setName",String.class);
        declaredMethod.invoke(o, "张飞");
        Method getName = c.getDeclaredMethod("getName");
        Object invoke = getName.invoke(o);
        System.out.println(invoke);
    }
}
