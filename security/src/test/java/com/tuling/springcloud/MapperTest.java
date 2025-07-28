package com.tuling.springcloud;

import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = SecurityApplication.class)
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Test
    public void sfsdfsdf(){
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        System.out.println(userInfos);
    }
}
