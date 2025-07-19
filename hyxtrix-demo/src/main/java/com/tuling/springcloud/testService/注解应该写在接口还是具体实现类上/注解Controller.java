package com.tuling.springcloud.testService.注解应该写在接口还是具体实现类上;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class 注解Controller {
    @Autowired
    @Qualifier("aaServiceImpl2")
    AaService aaService;

    @Resource(name="aaServiceImpl2")
    AaService aaServiceImpl22;

    @RequestMapping("hh")
    public String print(){
        aaServiceImpl22.print();
        return "成功";
    }
}
