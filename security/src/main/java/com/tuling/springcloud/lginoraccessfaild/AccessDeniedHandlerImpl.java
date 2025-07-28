package com.tuling.springcloud.lginoraccessfaild;

import com.alibaba.fastjson.JSON;
import com.tuling.springcloud.domain.ApiResponse;
import com.tuling.springcloud.util.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权失败的异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ApiResponse api = new ApiResponse(403,"客户权限不够",null);
        String s = JSON.toJSONString(api);
        WebUtil.renderString(httpServletResponse,s);
    }
}
