package com.tuling.springcloud.stock.设计模式.行为模式.迭代器模式;

public interface AbstractIterator {
    public void next(); //移至下一个元素
    public boolean isLast(); //判断是否为最后一个元素
    public void previous(); //移至上一个元素
    public boolean isFirst(); //判断是否为第一个元素
    public Object getNextItem(); //获取下一个元素
    public Object getPreviousItem(); //获取上一个元素
}
