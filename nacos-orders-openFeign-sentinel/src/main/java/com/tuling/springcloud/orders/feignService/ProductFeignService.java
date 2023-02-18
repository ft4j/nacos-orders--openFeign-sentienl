package com.tuling.springcloud.orders.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "nacos-product", path = "/product")
public interface ProductFeignService {
    @RequestMapping("/selectProduct/{index}")
    public String selectProduct(@PathVariable("index") Integer index);
}
