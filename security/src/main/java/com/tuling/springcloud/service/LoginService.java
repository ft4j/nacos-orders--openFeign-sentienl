package com.tuling.springcloud.service;

import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.cahce.UserinfoCache;
import com.tuling.springcloud.domain.ApiResponse;
import com.tuling.springcloud.domain.MyUserDetail;
import com.tuling.springcloud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    public ApiResponse login(UserInfo userInfo){
        //使用AuthenticationManager authenticate进行用户认证，他有多个实现类，一般来说使用账号密码进行校验
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userInfo.getUserName(), userInfo.getPassword());
        /**
         * authentication这个对象传入的账号密码和UserDetailServiceImpl.loadUserByUsername里根据用户名查询到的客户信息进行比对
         */
        Authentication authenticate = authenticationManager.authenticate(authentication);
        //认证通过就生成jwt，如果认证不通过就报错
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败了哦哦哦哦哦哦");
        }
        //这个返回的对象，就是UserDetailServiceImpl.loadUserByUsername的返回类型
        //TODO 登录的时候把数据客户信息数据和权限信息存入redis，这样就能在JwtAuthTokenFilter过滤器中添加权限
        MyUserDetail principal = (MyUserDetail)authenticate.getPrincipal();
        String username = principal.getUserInfo().getUserName();
        String token = JwtUtil.generateToken(username);
        //存入redis
        UserinfoCache.userinfoRedisMap.put(username,principal);
        return ApiResponse.success("登录成功！",token);
    }

    public ApiResponse logout(String token){
        String subject = JwtUtil.getSubject(token);
        UserinfoCache.userinfoRedisMap.remove(subject);
        return ApiResponse.success("注销成功",null);
    }
}
