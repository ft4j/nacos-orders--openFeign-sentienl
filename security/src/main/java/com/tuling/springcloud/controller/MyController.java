package com.tuling.springcloud.controller;

import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.util.JwtUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {
    @RequestMapping("ddd")
    @PreAuthorize("hasAnyAuthority('cccccc')")
    public String ddd(){
        //登录成功之后，token验证通过，缓存中的用户信息存入SecurityContextHolder，下面的方法可以直接获取用户信息
        UserInfo userInfo = JwtUtil.getUserInfo();
        System.out.println(userInfo);
        return "ddd";
    }

    @PreAuthorize("hasAnyAuthority('showBook')")
    @RequestMapping("showBook")
    public String ddddd(){
        return "showBook";
    }
    @PreAuthorize("hasAnyAuthority('deleteBook')")
    @RequestMapping("deleteBook")
    public String cddddd(){
        return "deleteBook";
    }
    @PreAuthorize("hasAnyAuthority('addBook')")
    @RequestMapping("addBook")
    public String zzz(){
        return "addBook";
    }


}
