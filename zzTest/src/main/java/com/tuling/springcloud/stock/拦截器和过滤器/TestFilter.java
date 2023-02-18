package com.tuling.springcloud.stock.拦截器和过滤器;

import javax.servlet.*;
import java.io.IOException;

public class TestFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器载入成功");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行过滤器");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
