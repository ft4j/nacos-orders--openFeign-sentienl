package com.tuling.springcloud.orders.controller;

import com.tuling.springcloud.orders.Aop.HuiduAnnotation;
import com.tuling.springcloud.orders.feignService.ProductFeignService;
import com.tuling.springcloud.orders.feignService.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("order")
public class OrderController {

    //    @Autowired
//    private RestTemplate restTemplate;
    String name;

    @Value("${user.name}")
    public void setPort(String name) {
        this.name = name;
    }

    @Autowired
    public StockFeignService stockFeignService;
    @Autowired
    public ProductFeignService productFeignService;

    @RequestMapping("add")
    @HuiduAnnotation
    public String add(String str) {
        System.out.println("下单成功"+str);
//        String forObject = restTemplate.getForObject("http://nacos-stock/stock/declineStock", String.class);
        String s = stockFeignService.declineStock();
        System.out.println(s);

//        String s1 = productFeignService.selectProduct(2);
//        System.out.println(s1);
        System.out.println(name + "???");
        return "下单成功";
    }

}
