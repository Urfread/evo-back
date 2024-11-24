package com.urfread.breaknews.core.learning.log;

import org.aspectj.lang.ProceedingJoinPoint;  // 使用 ProceedingJoinPoint
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeLoggerAspect.class);
    // 定义切点：拦截所有被 @LogExecutionTime 注解的方法
    @Pointcut("@annotation(com.urfread.breaknews.core.learning.log.LogExecutionTime)")
    public void logExecutionTimeMethods() {}

    // 环绕通知允许我们在方法执行前后做一些处理，
    @Around("logExecutionTimeMethods()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取方法名
        // proceedingJoinPoint.getSignature().getName() 用来获取被拦截方法的名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 记录方法执行开始时间
        long startTime = System.currentTimeMillis();
        // 执行被拦截的方法
        // proceedingJoinPoint.proceed() 执行被拦截的目标方法
        Object result = proceedingJoinPoint.proceed();  // 使用 proceed() 方法
        // 记录方法执行结束时间
        long endTime = System.currentTimeMillis();
        // 计算执行时间并记录日志
        long totalTime = endTime - startTime;
        logger.info("Method [{}] executed in {} ms", methodName, totalTime);
        // 返回方法执行结果
        return result;
    }
}
