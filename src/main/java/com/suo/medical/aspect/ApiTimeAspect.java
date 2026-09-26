package com.suo.medical.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
/**
 * 接口耗时切面
 */
public class ApiTimeAspect {

    @Around("execution(* com.suo.medical.controller..*.*(..))")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long cost = System.currentTimeMillis() - start;
        log.info("方法={},耗时={}ms",
                joinPoint.getSignature().getName(),
                cost
                );
        return result;
    }
}
