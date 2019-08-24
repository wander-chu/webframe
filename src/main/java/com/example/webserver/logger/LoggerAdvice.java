package com.example.webserver.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Before("within(com.example.webserver..*) && @annotation(loggerMessage)")
    //@Before("execution(* com.example.webserver..*.*(..)) && @annotation(loggerMessage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerMessage loggerMessage) {
        logger.info(String.format("执行%s开始", loggerMessage.description()));
        logger.info("执行方法：" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("执行参数:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "within(com.example.webserver..*) && @annotation(loggerMessage)", returning = "value")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerMessage loggerMessage, Object value) {
        logger.info(String.format("执行%s结束", loggerMessage.description()));
        logger.info("执行结果:" + value);
    }

    @AfterThrowing(pointcut = "within(com.example.webserver..*) && @annotation(loggerMessage)", throwing = "err")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerMessage loggerMessage, Exception err) {
        logger.error(String.format("执行%s异常", loggerMessage.description()), err);
    }
}
