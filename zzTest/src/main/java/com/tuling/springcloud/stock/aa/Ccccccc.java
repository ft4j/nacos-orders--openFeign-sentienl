package com.tuling.springcloud.stock.aa;

import org.springframework.beans.factory.annotation.Autowired;

@AnnotationForTest
public class Ccccccc {

    @Autowired
    Interfacesss interfacesss;

    public String name;
    public Integer s;
    @AnnotationForTest
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getS() {
        return s;
    }
    @AnnotationForTest
    public void setS(Integer s) {
        this.s = s;
    }
}
