package com.tuling.springcloud.lginoraccessfaild;

import com.alibaba.fastjson.JSON;
import com.tuling.springcloud.domain.ApiResponse;
import com.tuling.springcloud.util.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登录失败的异常  其实可以不处理，不过为了前端的统一处理，处理一下，这里甚至将请求状态设置为200，只是在返回的json对象中，把状态设置为401
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ApiResponse api = new ApiResponse(401,"客户登录失败",null);
        String s = JSON.toJSONString(api);
        WebUtil.renderString(httpServletResponse,s);
    }
}
