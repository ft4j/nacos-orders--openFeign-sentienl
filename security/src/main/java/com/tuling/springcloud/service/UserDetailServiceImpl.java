package com.tuling.springcloud.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.cahce.UserinfoCache;
import com.tuling.springcloud.domain.MyUserDetail;
import com.tuling.springcloud.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 这块加载的代码实际上只会在登录的时候，加载一次  UserDetailServiceImpl的27行调用
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //TODO 这是进行正好密码验证的地方，验证通过可以返回查询到的用户信息，
        System.out.println("替换后的校验服务");
        //用户登录的相关信息
        LambdaQueryWrapper<UserInfo> qw = new LambdaQueryWrapper<UserInfo>();
        qw.eq(UserInfo::getUserName,userName);
        UserInfo userInfo = userInfoMapper.selectOne(qw);
        if(Objects.isNull(userInfo)){
            // TODO 要知道，这里只是根据账号查询出了账号信息，实际的比对并不发生在这里，实际在下面的方法中
            //org.springframework.security.authentication.dao.DaoAuthenticationProvider.additionalAuthenticationChecks
            throw new RuntimeException("账号错了啊啊啊啊啊");
        }
        // TODO 用户权限校验的相关信息   这可以保存在数据库的！
//        List rolesList = Arrays.asList("myRole","youRole","hisRole","zzz");
        List<String> strings = userInfoMapper.queryPermission(userName);
        System.out.println(strings);
        //把数据封装成userDtails
        return new MyUserDetail(userInfo,strings);
    }
}
