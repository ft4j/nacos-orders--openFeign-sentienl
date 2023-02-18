package com.tuling.springcloud;

import com.tuling.springcloud.orders.OrdersFeignSentinelApplication;
import com.tuling.springcloud.orders.controller.JvmController;
import com.tuling.springcloud.orders.domain.Uu;
import com.tuling.springcloud.orders.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OrdersFeignSentinelApplication.class)
@RunWith(SpringRunner.class)
public class DDTest {
    @Autowired
    UserService userService;
    @Test
    public void dsdsd(){
        userService.ddd();
    }

    @Test
    public void dsd(){
        String s = "a"+"b";
        String s1 = "ab";
        System.out.println(s==s1);

        Class s2 = null;
    }
    @Autowired
    JvmController jvmController;

    @Test
    public void asdasd(){
        Uu u = new Uu();
        u.setEmail("heiha");
        String dd = jvmController.dd(u);
        System.out.println(dd);
    }


}
//运行时数据区，字节码执行引擎，类加载器