package com.tuling.springcloud;

import com.tuling.springcloud.orders.domain.EqualsEntity;
import org.junit.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class 集合Test {

    @Test
    public void sdfsdfsd(){
        Map<String,String> m = new ConcurrentHashMap<String,String>();
        m.put("key2","");
        m.put("key2","x");
        m.put("key14","");
        m.put("key25","");
        m.put("key36","ccc");


        String s = m.get("key36");
        Map<String,String> ss = new HashMap<String,String>();
        ss.put("","");
    }
    @Test
    public void sdfsdfsaaaaaad() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("applexzzz", 999);
        // 对键为"apple"的值加 2    compute方法可以让不存在的键值存入map中或线程安全地堆value进行计算
        //由于这个方法包括了put的功能所以，key也作为参数传入了
        map.compute("apple", (k, v) -> 7);
        System.out.println(map.get("apple")); // 输出 7
        //如果key：apple不存在，那么将key：applex value：4存入map，如果存在则不操作
        map.computeIfAbsent("applex", k -> 4);
        System.out.println(map.get("applex")); // 输出 4
        //如果applexzzz存在，就将applexzzz的value设为90，如果不存在，则不会将applexzzz的键值对存入map中
        map.computeIfPresent("applexzzz", (k, v) -> 90);
        System.out.println(map.get("applexzzz")); // 输出 90
    }
    @Test
    public void fsdfdf(){
        int i1 = 6546841 ^ 15;
        System.out.println(i1);
    }

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & 0x7fffffff;
    }

    @Test
    public void ssdfsdf(){
        int s = 5;

        int d = Integer.numberOfLeadingZeros(s) | (1 << (13 - 1));

        System.out.println(3|5);

        System.out.println(true & true);
    }

    @Test
    public void sdfsdf(){
        for (;;){
            System.out.println("dddd");
        }
    }


    @Test
    public void dfd(){
        System.out.println(dd());
    }

    /**
     * 执行结果
     * fianlly了
     * 返回了
     * 说明先finally再走到return，并且如果finally中带return，那么就返回finally中的return
     * 否则等finally执行完，再执行try中的return
     * @return
     */
    public String dd(){
        try{
            return "返回了";
        }catch(Exception e){
            System.out.println("catch了");
            e.printStackTrace();
            return "catch返回了";
        }finally {
            System.out.println("fianlly了");
//            return "finally返回了";
        }
    }


    @Test
    public void 对比list是否相同(){
        String[] str1 = new String[]{"4","7","11","6","1","r","s"};
        String[] str2 = new String[]{"11","r","4","s","1","7","6"};
        String[] str3 = {"","","",""};
        List<String> strings = Arrays.asList(str1);
        List<String> strings1 = Arrays.asList(str2);

        Collections.sort(strings);
        Collections.sort(strings1);
        System.out.println(strings);
        System.out.println(strings1);

        System.out.println("-------------------------");
        Integer[] int1 = new Integer[]{4,7,11,6,1};
        Integer[] int2 = new Integer[]{11,4,1,7,6};
        List<Integer> integers = Arrays.asList(int1);
        List<Integer> integers1 = Arrays.asList(int2);

        Collections.sort(integers);
        Collections.sort(integers1);
        System.out.println(integers);
        System.out.println(integers1);

        System.out.println("--------------------");
        System.out.println(strings.equals(strings1));
        System.out.println(integers.equals(integers1));
        System.out.println("-----------------------------");
        EqualsEntity ee = new EqualsEntity("z","c","x");
        EqualsEntity ee1 = new EqualsEntity("z1","c1","x");
        EqualsEntity eee = new EqualsEntity("z1","c1","x");
        EqualsEntity eee1 = new EqualsEntity("z","c","x");
        List<EqualsEntity> list = new ArrayList();
        list.add(ee);
        list.add(ee1);
        List<EqualsEntity> list1 = new ArrayList();
        list1.add(eee);
        list1.add(eee1);

        Collections.sort(list);
        Collections.sort(list1);

        System.out.println(list.equals(list1));

    }

    @Test
    public void sds(){
        EqualsEntity ee = new EqualsEntity("z","c","x");
        EqualsEntity ee2 = new EqualsEntity("z1z","cz","xc");
        EqualsEntity ee1 = new EqualsEntity("z1","c1","x");
        System.out.println(ee.hashCode());
        System.out.println(ee2.hashCode());
        System.out.println(ee1.hashCode());

        List<EqualsEntity> list = new ArrayList();
        list.add(ee);
        list.add(ee2);
        list.add(ee1);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void 比较两个list是否一致(){
        EqualsEntity ee = new EqualsEntity("z","c","x");
        EqualsEntity ee2 = new EqualsEntity("z1z","cz","xc");
        EqualsEntity ee1 = new EqualsEntity("z1","c1","x");


        EqualsEntity eee = new EqualsEntity("z","c","x");
        EqualsEntity eee2 = new EqualsEntity("z1z","cz","xc");
        EqualsEntity eee1 = new EqualsEntity("z1","c1","x");

        List<EqualsEntity> list1 = new ArrayList();
        list1.add(ee);
        list1.add(ee2);
        list1.add(ee1);

        List<EqualsEntity> list2 = new ArrayList();
        list2.add(eee2);
        list2.add(eee);
        list2.add(eee1);
        System.out.println(list1);
        System.out.println(list2);
        list1.removeAll(list2);
        System.out.println(list1);

    }

    @Test
    public void 两个对象的对比(){
        EqualsEntity ee = new EqualsEntity("z","c","x");
        EqualsEntity ee2 = new EqualsEntity("z","c","xczczxcc");
        boolean equals = ee.equals(ee2);
        System.out.println(equals);
    }

    @Test
    public void dddsdas() throws InterruptedException {
        HashMap m = new HashMap();
        Object put = m.put("1", "2");
        Object put1 = m.put("1", "3");
        System.out.println(put);//这是null
        System.out.println(put1);//这是2，那个被替换的value会被返回
    }

    @Test
    public void sdsdfsdf(){
        Set s = new HashSet();
        boolean ddd = s.add("ddd");//true
        System.out.println(ddd);
        boolean dddd = s.add("ddd");//false 元素已经存在了
        System.out.println(dddd);
    }

}
