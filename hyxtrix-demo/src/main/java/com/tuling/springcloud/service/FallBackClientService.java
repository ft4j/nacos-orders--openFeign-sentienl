package com.tuling.springcloud.service;

import com.tuling.springcloud.openFeignInterface.FallBackFactoryClient;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FallBackClientService implements FallBackFactoryClient {
    @Override
    public ResponseDto hystrixDest(RequestDto requestDto) {
        System.out.println("fallBack 进来了!!!!!!!!!!!!!!");
        return null;
    }
}
