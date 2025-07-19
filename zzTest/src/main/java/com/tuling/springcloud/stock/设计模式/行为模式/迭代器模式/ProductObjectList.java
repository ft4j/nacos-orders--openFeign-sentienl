package com.tuling.springcloud.stock.设计模式.行为模式.迭代器模式;

import java.util.List;

public class ProductObjectList extends AbstractObjectList {
    public ProductObjectList(List objects) {
        super(objects);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
