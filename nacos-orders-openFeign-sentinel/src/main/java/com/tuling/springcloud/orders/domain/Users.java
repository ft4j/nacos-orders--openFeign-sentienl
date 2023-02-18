package com.tuling.springcloud.orders.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class Users implements Cloneable{
    public Users(){}
    public Users(String name){
        this.name = name;
    }
    private String name;
    private String age;
    private JustObject justObject;

    public JustObject getJustObject() {
        return justObject;
    }

    public void setJustObject(JustObject justObject) {
        this.justObject = justObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Users clone()throws CloneNotSupportedException{
        Users s = (Users)super.clone();
        JustObject clone = justObject.clone();
        s.setJustObject(clone);
        return s;
    }
}
