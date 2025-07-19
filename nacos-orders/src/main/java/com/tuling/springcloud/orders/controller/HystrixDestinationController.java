package com.tuling.springcloud.orders.controller;

import com.tuling.springcloud.orders.reqAndRes.RequestDto;
import com.tuling.springcloud.orders.reqAndRes.ResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixDestinationController {

    @RequestMapping("hystrixDest")
    public ResponseDto hystrixDest(@RequestBody RequestDto requestDto){
        System.out.println("hystrixDest被请求到了");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setRespCode("200");
        responseDto.setRespMessage("接口适配成功"+requestDto.getId()+requestDto.getName());
        int i = 1/0;
        return responseDto;
    }
}
