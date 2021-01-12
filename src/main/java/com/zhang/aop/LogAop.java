package com.zhang.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: zhang
 * @Description: com.zhang.aop
 * @Date：Created in 10:01 2021/1/11
 */
@Component
@Aspect
public class LogAop {
    Logger logger=Logger.getLogger(LogAop.class);
    @Around("execution(public * com.zhang.service.*.*(..))")
    public Object doAroundLogs(ProceedingJoinPoint pjp) throws Throwable {
        try{
            Object retVal = pjp.proceed();
            return retVal;
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
