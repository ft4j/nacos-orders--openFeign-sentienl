package com.tuling.springcloud.stock.设计模式.行为模式.迭代器模式;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectList {
    protected List<Object> objects = new ArrayList<Object>();

    public AbstractObjectList(List objects){
        this.objects = objects;
    }

    public void addObject(Object o){
        objects.add(o);
    }

    public void removeObject(Object o){
        objects.remove(o);
    }

    public List getObjects() {
        return this.objects;
    }

    public abstract AbstractIterator createIterator();
}
