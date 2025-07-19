package com.tuling.springcloud.service;

import com.tuling.springcloud.openFeignInterface.FallBackFactoryClient;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * hystrix直接指定fallback就是直接让指定的类负责处理异常
 * 使用fallbackFactory可以指定一个Feign的接口类的某一个实现类去处理某一种异常
 */
@Component
public class FallBackFactoryClientService implements FallbackFactory<FallBackClientService> {
    @Override
    public FallBackClientService create(Throwable cause) {
        System.out.println("fallBack 进来了??????????????");
        cause.printStackTrace();
        return new FallBackClientService() {
            @Override
            public ResponseDto hystrixDest(RequestDto requestDto) {
                System.out.println("fallBack 进来了xxxxxxxxxxxxxxxxx");
                ResponseDto dto = new ResponseDto();
                dto.setRespCode("xxxxxx");
                dto.setRespMessage("好的");
                if(cause instanceof NullPointerException){//假如是空指针异常，就设置额外的处理分支
                    dto.setRespCode("yyyyy");
                    dto.setRespMessage("这是空指针处理");
                }
                return dto;
            }
        };
    }
}
