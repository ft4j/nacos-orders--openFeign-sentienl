package com.tuling.springcloud.stock.设计模式.结构模式.适配器模式;

public class Adapter extends SpringReposiroty implements SpringController {

    @Override
    public void getUserName(String id) {
        String c = queryUserName(Integer.parseInt(id));
    }
}
