package com.tuling.springcloud.orders.domain;

import lombok.Data;

@Data
public class Student {
    private String age;
    private String name;
    private static User user;
    static{
        user = new User("","");
        System.out.println("cccstatic");
    }
    {
        System.out.println("ddd");
    }
    public Student(){
        System.out.println("构造方法");
    }
}
