package com.tuling.springcloud.orders.Aop;

import com.tuling.springcloud.orders.ThreadLocalPackage.ParamsBand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HuiduAspect {
    @Pointcut("@annotation(com.tuling.springcloud.orders.Aop.HuiduAnnotation)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        ParamsBand.setParam((String) args[0]);

        Object o = null;
        try {
            o = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }
}
