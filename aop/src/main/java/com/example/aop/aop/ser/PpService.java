package com.example.aop.aop.ser;

import org.springframework.stereotype.Service;

@Service
public class PpService {
    public void printtt(){
        System.out.println("往切面里写点东西,hhhhhhhh");
    }
}
