package com.tuling.springcloud.openFeignInterface;

import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import com.tuling.springcloud.service.FallBackFactoryClientService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "nacos-order",fallbackFactory = FallBackFactoryClientService.class,contextId = "nacos-discovery-8")
public interface FallBackFactoryClient {
    @RequestMapping("hystrixDest")
    public ResponseDto hystrixDest(RequestDto requestDto);
}
