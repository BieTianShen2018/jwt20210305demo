package com.jjwt.demo20210305.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@WebFilter(filterName = "test", urlPatterns = "/*")
public class UrlFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(UrlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MDC.put("testLog", UUID.randomUUID().toString());
       logger.debug ("----------------------->过滤器被创建1");
        logger.info ("----------------------->过滤器被创建2");
        logger.warn ("----------------------->过滤器被创建3");
        logger.error ("----------------------->过滤器被创建4");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put("testLog", UUID.randomUUID().toString());
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        logger.error("--------------------->过滤器：请求地址"+requestURI);
        if(!requestURI.contains("info")){
            servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

        logger.error("----------------------->过滤器被销毁");
        MDC.remove("testLog");
    }
}