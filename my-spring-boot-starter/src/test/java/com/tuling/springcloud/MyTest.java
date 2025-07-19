package com.tuling.springcloud;

import com.tuling.springcloud.digest.Digest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class MyTest {
    @Autowired
    Digest digest;
    @Test
    public void dddddd(){
        String encrypt = digest.encrypt("nihao!");
        System.out.println(encrypt);
    }


}
