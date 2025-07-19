package com.example.aop.aop.ser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Aaa {
    @Transactional
    public void ddd(){

    }
}
