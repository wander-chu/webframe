package com.example.webserver.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    private static Logger logger = LoggerFactory.getLogger(TimeAspect.class);

    @Around("execution(* com.example.webserver.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("time aspect start");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            logger.info("arg is: " + arg);
        }
        Long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed(args);
        logger.info("result is: " + result);
        Long endTime = System.currentTimeMillis();
        logger.info("time aspect consume {} ms", endTime - startTime);
        logger.info("time aspect end");
        return result;
    }
}
