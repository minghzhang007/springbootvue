package com.lewis.sprinbootvue.biz.core;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long begin = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.println("costTime#" + (System.currentTimeMillis() - begin));
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destroy...");
    }
}
