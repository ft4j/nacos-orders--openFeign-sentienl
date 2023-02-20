package com.tuling.springcloud.orders.domain;

import lombok.Data;

import java.util.Objects;
@Data
public class EqualsEntity implements Comparable {
    public EqualsEntity(){}
    public EqualsEntity(String name,String age,String degree){
        this.name = name;
        this.age = age;
        this.degree = degree;
    }
    private String name;
    private String age;
    private String degree;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsEntity that = (EqualsEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(age, that.age) &&
                Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, degree);
    }

    @Override
    public int compareTo(Object o) {
        if(this.hashCode() > o.hashCode()){
            return 1;
        }else if(this.hashCode() == o.hashCode()){
            return 0;
        }else{
            return -1;
        }
    }
}
