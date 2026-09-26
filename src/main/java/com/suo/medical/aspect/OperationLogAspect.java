package com.suo.medical.aspect;

import com.suo.medical.annotation.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面：OperationLogAspect
 */

@Aspect
//切面也要交给Spring容器来进行管理
@Component
@Slf4j
public class OperationLogAspect {
    @Around(value = "@annotation(operationLog)")//切点：拦截所有标了@OperationLog注解的方法
    public Object around(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        long start = System.currentTimeMillis();

        try{
            Object result = joinPoint.proceed();
            long costFirst = System.currentTimeMillis() - start;
            log.info(
                    "操作={},结果={},耗时={}ms",
                    operationLog.value(),
                    "成功",
                    costFirst
            );
            return result;
        }catch (Exception e){
            long costSecond = System.currentTimeMillis() - start;
            log.info(
                    "操作={},结果={},耗时={}ms",
                    operationLog.value(),
                    "失败",
                    costSecond
            );
            throw e;
        }

//        long costFinal = System.currentTimeMillis() - start;
//        //joinPoint.getSignature().getName()获取方法名，joinPoiont.getArgs()获取参数
//        log.info("操作={},方法={},参数={},耗时={}ms",
//                    operationLog.value(),
//                    joinPoint.getSignature().getName(),
//                    Arrays.toString(joinPoint.getArgs()),
//                    costFinal
//                    );
//        return result;
    }

    @Before("@annotation(operationLog)")
    public void before(JoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        log.info("======操作={},方法={},参数={}"+"，开始执行！！！======",
                operationLog.value(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @After("@annotation(operationLog)")
    public void after(JoinPoint joinPoint,OperationLog operationLog) throws Throwable {
        log.info("======方法={},参数={}，执行结束！！！======",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning("@annotation(operationLog)")
    public void afterReturning(JoinPoint joinPoint,OperationLog operationLog) throws Throwable {
        log.info("======方法={},参数={}，执行结束！！！======",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing("@annotation(operationLog)")
    public void beforeThrowing(JoinPoint joinPoint,OperationLog operationLog) throws Throwable {
        log.info("======方法={},参数={}，执行结束！！！======",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

}
