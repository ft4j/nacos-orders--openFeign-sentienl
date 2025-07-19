package com.tuling.springcloud;

import com.tuling.springcloud.openFeignInterface.FallbackNullFeignClient;
import com.tuling.springcloud.reqAndRes.RequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = HystrixApplication.class)
@RunWith(SpringRunner.class)
public class FeignTest {
    @Autowired
    FallbackNullFeignClient client;

    @Test
    public void sfsdfds(){
        try{
            RequestDto dto = new RequestDto();
            client.hystrixDest(dto);
        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
    }
}
