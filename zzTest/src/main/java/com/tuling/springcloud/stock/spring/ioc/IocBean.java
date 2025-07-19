package com.tuling.springcloud.stock.spring.ioc;

public class IocBean {
    public IocBean(){}
    public IocBean(String secondName){
        this.secondName = secondName;
    }
    public void init(){
        System.out.println("这是一个init方法，bean在实例化时，会调用它");
    }
    public void beanMethod(){
        System.out.println("打印一下");
    }

    public String firstName;
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String secondName;
}
