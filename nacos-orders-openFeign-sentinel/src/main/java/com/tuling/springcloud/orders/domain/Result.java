package com.tuling.springcloud.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;
    public Result(){}
    public Result(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public static Result error(String code,String msg){
        return new Result(code,msg);
    }
    public static Result error(String code,String msg,String t){
        return new Result(code,msg,t);
    }

}
