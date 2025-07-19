package com.tuling.springcloud.orders.domain;

import lombok.Data;

@Data
public class SortEntity implements Comparable<SortEntity>{
    public SortEntity(Integer age){
        this.age = age;
    }
    private String name;
    private Integer age;

    @Override
    public int compareTo(SortEntity o) {
        if(o.getAge()>this.age){
            return 1;
        }else if(this.age==o.getAge()){
            return 0;
        }else{
            return -1;
        }
    }
}
