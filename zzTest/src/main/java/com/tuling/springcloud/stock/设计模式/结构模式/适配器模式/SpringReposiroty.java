package com.tuling.springcloud.stock.设计模式.结构模式.适配器模式;

public class SpringReposiroty {
    public String queryUserName(Integer s){
        if(s == 2){
            return "李四";
        }
        return "张三";
    }
}
