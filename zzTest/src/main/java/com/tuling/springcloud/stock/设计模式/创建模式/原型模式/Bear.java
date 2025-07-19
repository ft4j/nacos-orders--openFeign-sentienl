package com.tuling.springcloud.stock.设计模式.创建模式.原型模式;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Data
@Scope
public class Bear implements Cloneable{
    private String jiao;
    private Integer tizi;
    private String du;
    private List list;

    public Bear clone() throws CloneNotSupportedException {
        Bear br = (Bear) super.clone();
        return br;
    }
}
