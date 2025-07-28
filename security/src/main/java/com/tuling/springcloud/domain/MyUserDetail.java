package com.tuling.springcloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuling.springcloud.cahce.UserInfo;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MyUserDetail implements UserDetails {
    private UserInfo userInfo;
    private List<String> list;

    public MyUserDetail(UserInfo userInfo, List<String> list) {
        this.userInfo = userInfo;
        this.list = list;
    }

    //权限对象
    @JsonIgnore
    List<SimpleGrantedAuthority> collect;
    //返回权限的
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //不知道为什么是要添加判空
        if(!Collections.isEmpty(collect)){
            return collect;
        }
        //把权限集合中的角色名称封装为SimpleGrantedAuthority，并返回    TODO 构造器实际上是一个有返回值的方法，可以对Functional进行方法引用
        return list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }
    //判断是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
