package com.example.webserver.cache;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
    private static Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Autowired
    private Cache cache;

    @Around("execution(* com.example.webserver.service.*.*(..))")
    public Object handleServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object cached = cache.get(methodName);
        if (cached != null) {
            logger.info("get value from cache");
            return cached;
        }
        Object result = proceedingJoinPoint.proceed(args);
        cache.save(methodName, result);
        return result;
    }
}
