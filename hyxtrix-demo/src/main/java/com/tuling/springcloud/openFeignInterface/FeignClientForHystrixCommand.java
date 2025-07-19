package com.tuling.springcloud.openFeignInterface;

import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name= "nacos-order",contextId = "nacos-discovery-1")
public interface FeignClientForHystrixCommand {

    @RequestMapping("hystrixDest")
    public ResponseDto hystrixDest(RequestDto requestDto);


}
