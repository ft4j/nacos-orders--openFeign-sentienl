package com.tuling.springcloud.testService.注解应该写在接口还是具体实现类上;

import org.springframework.stereotype.Service;

@Service
public class AaServiceImpl1 implements AaService {
    @Override
    public void print() {
        System.out.println("111111111");
    }
}
