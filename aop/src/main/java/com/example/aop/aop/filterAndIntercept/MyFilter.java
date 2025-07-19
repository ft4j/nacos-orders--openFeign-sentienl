package com.example.aop.aop.filterAndIntercept;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(urlPatterns = "/dddd")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("initMyFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器前置前置前置aaaaa");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("过滤器后置后置后置bbbbb");
    }

    @Override
    public void destroy() {
        System.out.println("destroyMyFilter");
    }
}
