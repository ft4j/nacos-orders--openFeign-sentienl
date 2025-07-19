package com.tuling.springcloud.service;

import com.tuling.springcloud.openFeignInterface.FallbackNullFeignClient;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FallbackNullFeignClientService implements FallbackNullFeignClient {
    @Override
    public ResponseDto hystrixDest(RequestDto requestDto) {
        System.out.println("fallBackLe a  a   a");
        return null;
    }
}
