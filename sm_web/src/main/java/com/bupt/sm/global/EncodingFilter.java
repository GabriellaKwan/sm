package com.bupt.sm.global;

import javax.servlet.*;
import java.io.IOException;

/**
 * 编码过滤器
 * **/
public class EncodingFilter implements Filter  {
    //读取初始化参数中的指定的编码
    private String encoding = "UTF-8";//赋初值为UTF-8
    //如果用户配置了编码，就把用户配置的编码赋值给encoding，
    //如果用户没有配置编码，就是初始值UTF-8

    //初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("ENCODING")!=null)//防止返回值为空（没配置就是null）
            encoding = filterConfig.getInitParameter("ENCODING");
    }

    //过滤方法
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //请求和响应的时候，都需要设置编码
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        //编码过滤器，不需要任何拦截，直接放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁方法
    public void destroy() {
        encoding=null;
    }
}
