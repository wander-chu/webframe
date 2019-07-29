package com.example.webserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);
    private final NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("startTimeThreadLocal");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("time interceptor preHandler");
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        logger.info("handler class: " + handlerMethod.getBeanType().getName());
        logger.info("handler method: " + handlerMethod.getMethod().getName());
        startTimeThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("time interceptor postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("time interceptor afterCompletion");
        Long startTime = startTimeThreadLocal.get();
        Long endTime = System.currentTimeMillis();
        logger.info("time interceptor consume {} ms", endTime - startTime);
    }
}
