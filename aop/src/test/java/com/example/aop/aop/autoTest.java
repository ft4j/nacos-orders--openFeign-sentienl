package com.example.aop.aop;

import com.example.aop.aop.auto.Abstracttt;
import com.example.aop.aop.auto.Coll;
import com.example.aop.aop.auto.Inte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class autoTest {
    @Autowired
    Coll coll;
    @Autowired
    Map<String,Abstracttt> abstracttt;
    @Test
    public void dsdfsdf(){
        Map<String, Abstracttt> map = coll.getMap();
        Abstracttt secondClass = map.get("secondClass");
        secondClass.dd();
        secondClass.cc();
        System.out.println(map);
        System.out.println(abstracttt);
    }
}
