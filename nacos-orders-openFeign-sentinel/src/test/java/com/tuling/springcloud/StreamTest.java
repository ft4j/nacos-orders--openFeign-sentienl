package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.SortEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void ddd(){
        SortEntity se1 = new SortEntity(1);
        SortEntity se2 = new SortEntity(3);
        SortEntity se3 = new SortEntity(11);
        SortEntity se4 = new SortEntity(2);
        List<SortEntity> l = new ArrayList();
        l.add(se1);
        l.add(se2);
        l.add(se3);
        l.add(se4);
        Stream<SortEntity> stream = l.stream();
        List<SortEntity> collect = stream.sorted().collect(Collectors.toList());
        System.out.println(collect);
        stream.close();
        //------------------------
        Stream<SortEntity> stream1 = l.stream();
        List<SortEntity> collect1 = stream1.filter(x -> {
            return x.getAge() == 1;
        }).collect(Collectors.toList());
        System.out.println(collect1);
        //---------------------
        Stream<SortEntity> stream2 = l.stream();
        Optional<SortEntity> first = stream2.findFirst();
        System.out.println(first);

        //--------------------
        Stream<SortEntity> stream3 = l.stream();
        List<SortEntity> collect2 = stream3.sorted().limit(4).collect(Collectors.toList());
        System.out.println(collect2);

        //-------------这个只有在并发比较高的时候才有随机性不然都是获得第一个元素
        Stream<SortEntity> stream4 = l.stream();
        Optional<SortEntity> any = stream4.findAny();
        System.out.println(any);

        //------------------

    }
}
