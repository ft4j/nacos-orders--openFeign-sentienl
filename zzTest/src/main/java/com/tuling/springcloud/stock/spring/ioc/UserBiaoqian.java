package com.tuling.springcloud.stock.spring.ioc;

import lombok.Data;

@Data
public class UserBiaoqian {
    public UserBiaoqian(){

    }
    public UserBiaoqian(String dd){
        this.dd = dd;
    }
    private String ss;
    private String dd;
}
