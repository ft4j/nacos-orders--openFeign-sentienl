package com.tuling.springcloud.hystrix;

import com.tuling.springcloud.openFeignInterface.FeignClientInt;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HystrixFallback implements FeignClientInt {
    @Override
    public ResponseDto hystrixDest(RequestDto requestDto) {
        String resMessage = "服务降级，稍后再试";
        System.out.println(resMessage);
        ResponseDto r = new ResponseDto();
        r.setRespCode("40000");
        r.setRespMessage("resMessage");
        return r;
    }
}
