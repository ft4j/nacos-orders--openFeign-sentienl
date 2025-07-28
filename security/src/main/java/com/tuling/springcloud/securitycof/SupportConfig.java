package com.tuling.springcloud.securitycof;

import com.tuling.springcloud.filter.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SupportConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//前后端分离项目需要关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//不再使用token进行验证
        .and().authorizeRequests()
                .antMatchers("/user/login").anonymous()//对登录接口进行豁免
                .antMatchers("/user/ccc").permitAll()//这表示无论是否登录，都可以访问，但是一般不会这么干
        .anyRequest().authenticated()//除此之外的接口全部不能豁免
                //将校验接口放在UsernamePasswordAuthenticationFilter这个过滤器之前
        .and().addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class)
        //添加登录失败处理器
        .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
        //添加权限不足处理器
        .accessDeniedHandler(accessDeniedHandler)
        ;
    }

    //账号密码是否通过校验的bean
    @Override
    @Bean//添加这个bean就能在容器中获取AuthenticationManager了
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 配置BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 可以指定工作因子（4-31之间，默认10），值越大加密越慢但越安全
        return new BCryptPasswordEncoder(12);
    }
}
