package com.tuling.springcloud.testService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAaController {
    @RequestMapping("tt")
    public String tt(@RequestBody @Validated AaDto dto){
        System.out.println(dto);
        return "测试成功";
    }
}
