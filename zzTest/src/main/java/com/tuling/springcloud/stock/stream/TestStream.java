package com.tuling.springcloud.stock.stream;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    @Test
    public void dd(){
        List<Integer> list = StreamSupport.getSupport();
        List<Integer> collect = list.stream().filter((x) -> {
            return x.equals(1);
        }).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("-------------------");
        //distinct需要用到对象中equals方法
        List<Integer> collect1 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println("-------------------");
        //sorted使用的是对象中的compareTo方法
        List<Integer> collect2 = list.stream().sorted().collect(Collectors.toList());
        System.out.println(collect2);
        System.out.println("-------------------");
        List<Integer> limit = list.stream().limit(3).collect(Collectors.toList());
        System.out.println(limit);
        System.out.println("-------------------");
        Map<Integer, List<Integer>> collect3 = list.stream().collect(Collectors.groupingBy(list::get));
        System.out.println(collect3);
    }
}
