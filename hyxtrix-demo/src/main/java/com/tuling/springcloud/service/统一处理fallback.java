package com.tuling.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;

@DefaultProperties(defaultFallback = "xx")
public class 统一处理fallback {
    public ResponseDto xx(RequestDto requestDto){
        System.out.println("--------------------------------------------------");
//        throwable.printStackTrace();
        System.out.println("--------------------------------------------------");
        System.out.println("fallback了1231231231231");
        ResponseDto dtp = new ResponseDto();
        dtp.setRespCode("1231231");
        return dtp;
    }
}
