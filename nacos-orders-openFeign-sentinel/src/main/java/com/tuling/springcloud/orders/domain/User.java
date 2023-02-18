package com.tuling.springcloud.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User<T> {
    public User(){}
    private String name;
    private String age;

    public void dd(String str){

    }
}
