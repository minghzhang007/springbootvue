package com.lewis.sprinbootvue.biz.core;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("begin",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long begin = (Long) request.getAttribute("begin");
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
            System.out.println(handlerMethod.getMethod()+" costTime:"+(System.currentTimeMillis()-begin));
        }else{
            System.out.println(" costTime:"+(System.currentTimeMillis()-begin));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
