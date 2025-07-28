package com.tuling.springcloud.filter;

import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.cahce.UserinfoCache;
import com.tuling.springcloud.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token  该项目使用token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //直接放行，后续会判断是否是认证状态，如果是这里放行的话，后续会被校验住，访问不成功
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String subject = "";
        try{
            subject = JwtUtil.getSubject(token);//如果超时，这这个方法也是会报错的，可以代替validation方法
        }catch (Exception e){
            logger.info("token解析出错",e);
            throw new RuntimeException("token解析出错");
        }
        //从redis获取客户信息  TODO 数据登录的时候放进去的
        UserInfo userInfo = UserinfoCache.userinfoRedisMap.get(subject).getUserInfo();
        Collection<? extends GrantedAuthority> authorities = UserinfoCache.userinfoRedisMap.get(subject).getAuthorities();

        if(Objects.isNull(userInfo)){
            throw new RuntimeException("无法获取客户信息缓存，用户未登录，校验失败");
        }
        //存入SecurityContextHolder 只有它存入了信息后续才会认为通过了校验
        //TODO 第一个参数是客户信息主题，第二个是密码这个一般是null，第三个是权限信息
        // TODO 其实这一块可以不做，在controller中获取token，解析之后从redis拿权限和客户信息也是一样的
        // TODO 这里不过是往SecurityContextHolder.getContext()放入数据，方便后续使用罢了
        Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo
                ,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
