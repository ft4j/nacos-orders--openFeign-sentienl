package com.tuling.springcloud.stock.controller;

import com.tuling.springcloud.stock.spring.transaction.entity.Deletetesttable;
import com.tuling.springcloud.stock.spring.transaction.repository.DeletetesttableRepositoryImpl;
import jodd.util.MathUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MybatisPlusController {

    @Autowired
    DeletetesttableRepositoryImpl deletetesttableRepository;

    @RequestMapping("ddd")
    public void dddd(){
        for (int i = 0; i < 500000; i++) {
            Deletetesttable dtt = new Deletetesttable();
            dtt.setId(i);
            deletetesttableRepository.save(dtt);
        }
    }
}
