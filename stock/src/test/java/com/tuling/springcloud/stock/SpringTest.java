package com.tuling.springcloud.stock;


import com.tuling.springcloud.stock.Service.AdaptBean;
import com.tuling.springcloud.stock.Service.AdaptBean2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = StockApplication.class)
@RunWith(SpringRunner.class)
public class SpringTest {
    @Autowired
    AdaptBean adaptBean;

    @Autowired
    AdaptBean2 adaptBean2;
    @Test
    public void ddfd(){
        adaptBean.cc();
        adaptBean2.dd();
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void sdfsdf(){
        Object adaptBean = applicationContext.getBean("adaptBean");
        System.out.println(adaptBean);
    }

}
