package com.tuling.springcloud.orders.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Uu {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Date updateTime;
    private Date createTime;
}
