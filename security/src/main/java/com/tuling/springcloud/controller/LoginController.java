package com.tuling.springcloud.controller;

import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.cahce.UserinfoCache;
import com.tuling.springcloud.domain.ApiResponse;
import com.tuling.springcloud.service.LoginService;
import com.tuling.springcloud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginService loginService;

    //这个接口是要在未认证的情况下进行放行的！
    @RequestMapping("/user/login")
    public ApiResponse sign(@RequestBody UserInfo userInfo){
        return loginService.login(userInfo);
    }
    @RequestMapping("/user/logout")
    public ApiResponse logout(@RequestHeader String token){
        return loginService.logout(token);
    }

    @RequestMapping("/user/ccc")
    @PreAuthorize("hasAnyAuthority('myRole','youRole')")
    public String ccc(){
        return "/user/ccc";
    }


}
