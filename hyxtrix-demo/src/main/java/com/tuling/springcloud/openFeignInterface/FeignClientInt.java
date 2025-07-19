package com.tuling.springcloud.openFeignInterface;

import com.tuling.springcloud.hystrix.HystrixFallback;
import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name= "nacos-order",fallback = HystrixFallback.class,contextId = "nacos-discovery-0")
public interface FeignClientInt {

    @RequestMapping("hystrixDest")
    public ResponseDto hystrixDest(RequestDto requestDto);

}
