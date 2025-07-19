package com.tuling.springcloud.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class ControIntercept implements  HandlerInterceptor {
    /**
     * 在controller调用之前执行
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String string1 = request.getParameter("string1");
        if(string1.equals("123")){
            return true;
        }
        System.out.println(request.getRequestURI()+"开始执行");
        return false;
    }

    /**
     * 在controller调用中执行
     */
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    /**
     * 在controller调用后执行
     */
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(request.getRequestURI()+"执行结束");
    }

}
