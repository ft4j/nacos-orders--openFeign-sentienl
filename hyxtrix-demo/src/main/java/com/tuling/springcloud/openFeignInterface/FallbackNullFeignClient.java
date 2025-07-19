package com.tuling.springcloud.openFeignInterface;

import com.tuling.springcloud.reqAndRes.RequestDto;
import com.tuling.springcloud.reqAndRes.ResponseDto;
import com.tuling.springcloud.service.FallbackNullFeignClientService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "nacos-order",fallback = FallbackNullFeignClientService.class,contextId = "nacos-discovery-5")
public interface FallbackNullFeignClient {
    @RequestMapping("hystrixDest")
    public ResponseDto hystrixDest(RequestDto requestDto);
}
