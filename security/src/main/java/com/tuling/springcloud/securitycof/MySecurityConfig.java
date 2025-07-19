package com.tuling.springcloud.securitycof;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

//@Configuration
//@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 权限配置  白名单  JWT认证等等
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
//        //对哪些请求进行放行
//        registry.antMatchers("/ddd/**","/cc/**").permitAll();
//        registry.anyRequest().authenticated();
        //允许跨域  允许可以请求options cors
//        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
//        //其他任何都需要实名认证
//        registry.anyRequest().authenticated()
//        //关闭csrf跨站请求伪造，因为现在使用jwt来实现认证
//        .and().csrf().disable();
                //进制session
    }
}
