package com.tuling.springcloud.stock.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamSupport {
    public static List<Integer> getSupport(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(2);
        list.add(5);
        return list;
    }

}
