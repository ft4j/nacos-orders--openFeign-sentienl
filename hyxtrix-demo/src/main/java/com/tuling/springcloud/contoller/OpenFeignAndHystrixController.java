package com.tuling.springcloud.contoller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tuling.springcloud.openFeignInterface.FallBackFactoryClient;
import com.tuling.springcloud.openFeignInterface.FallbackNullFeignClient;
import com.tuling.springcloud.openFeignInterface.FeignClientInt;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import com.tuling.springcloud.service.OpenFeignAndHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignAndHystrixController {

    @Autowired
    FeignClientInt feignClientInt;

    @PostMapping("res")
    public ResponseDto res(@RequestBody RequestDto requestDto){
        ResponseDto responseDto = feignClientInt.hystrixDest(requestDto);
        return responseDto;
    }

    @Autowired
    OpenFeignAndHystrixService openFeignAndHystrixService;
    @PostMapping("ress")
    public ResponseDto ress(@RequestBody RequestDto requestDto){
        ResponseDto responseDto = openFeignAndHystrixService.hystrixDest(requestDto);
        return responseDto;
    }

    @PostMapping("resss")
    public ResponseDto resss(@RequestBody RequestDto requestDto){
        ResponseDto 本地接口 = openFeignAndHystrixService.本地接口(requestDto);
        return 本地接口;
    }


    @Autowired
    @Qualifier("fallbackNullFeignClientService")
    FallbackNullFeignClient client;
    @RequestMapping("fallBackNull")
    public ResponseDto fallBackNull(){
        ResponseDto responseDto = client.hystrixDest(null);
        return responseDto;
    }

    @Autowired
    FallBackFactoryClient fallBackFactoryClient;
    @RequestMapping("fallBackFactory")
    public ResponseDto fallBackFactory(){
        ResponseDto responseDto = fallBackFactoryClient.hystrixDest(null);
        return responseDto;
    }
}
