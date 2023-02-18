package com.tuling.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "nacos-stock",path = "/stock")
public interface ZaFeignService {

    @RequestMapping("/declineStock")
    public String gettt();

}
