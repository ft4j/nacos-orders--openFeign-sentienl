package com.tuling.springcloud.orders.springboot自动装配;

/**
 * 注解注入ZhuRuComponent对象
 */
public class F {
    public F(){}
    public F(String name, String age){
        this.name = name;
        this.age = age;
    }
    private String name;
    private String age;

    @Override
    public String toString() {
        return "ZhuRuComponent{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
