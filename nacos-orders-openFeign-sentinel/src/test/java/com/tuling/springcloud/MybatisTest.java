package com.tuling.springcloud;

import com.tuling.springcloud.orders.OrdersFeignSentinelApplication;
import com.tuling.springcloud.orders.domain.ResultMapBean;
import com.tuling.springcloud.orders.domain.Uu;
import com.tuling.springcloud.orders.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = OrdersFeignSentinelApplication.class)
@RunWith(SpringRunner.class)
public class MybatisTest {

    @Autowired
    private UserService userService;

    @Test
    public void sdsds(){
        Uu jone = userService.query1("Jone");
        System.out.println(jone);
    }

    @Test
    public void sdsddes(){
        List<Uu> jone = userService.query11("Jone");
        System.out.println(jone);
    }


    @Test
    public void sdasd(){
        Map jone = userService.queryForMap("Jone");
        System.out.println(jone);
    }

    @Test
    public void asdff(){
        ResultMapBean jone = userService.queryForResultMap("Jone");
        System.out.println(jone);
    }

    @Test
    public void asdff333(){
        List<ResultMapBean> jone = userService.queryForResultMap11("Jone");
        System.out.println(jone);
    }

    @Test
    public void asdffeeee333(){
        List<ResultMapBean> jone = userService.queryForObject("Jone");
        System.out.println(jone);
    }

}
