package com.tuling.springcloud.orders.domain;



public class JustObject implements Cloneable{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JustObject clone()throws CloneNotSupportedException{
        return (JustObject)super.clone();
    }
}
