package com.tuling.springcloud;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap原理 {
    /**
     * concurrentHashMap原理
     * 将key通过自带得方法转化为一个hash值
     * 判断底层数组是否存在，存在走下一步，不存在创建数据，循环继续判断
     * 如果存在，将key得hash值和底层数组的长度做一个按位与运算，结果就是这个key应该存放的位置
     * 判断这个位置是否存在值，不存在使用cas compareAndSwap设置值，跳出循环完成设置
     * 如果存在，用getObjectVolatile获取值，判断它的hash值是不是为-1
     * 如果是-1，表示该map正在被扩容，让本线程加入扩容程序，完成扩容并设值
     * 否则锁住该位置的树的第一个节点
     * 再次判断之前的节点和第一个是否是同一个（有可能在线程执行时，这个ConcurrentHashMap已经被扩容了）
     * 循环链表，判断每一个key和当前的key是否是同一个，如果是，就用新值覆盖
     * 如果不是，循环到最后一个节点，并在这个节点后面添加一个节点
     * 如果发现这个链表已经被转为红黑树，就用TreeBin对象往红黑树添加数据，此时的链表长度固定为2，不会触发转为红黑树的方法
     *
     * 添加完数据之后，判断链表的长度是否大于8，如果时，进入转红黑树方法，在该方法立判断底层数组长度，如果不大于64，就进行扩容
     * 如果大于64，开始转红黑树
     */
    @Test
    public void dddd() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("","");
        HashMap map1 = new HashMap();
        map1.put("","");

        ArrayList<Integer> l = new ArrayList();
        l.sort(Integer::compareTo);
        l.add(12);
    }
}
