package org.starry.webmanagement.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect that logs controller and service execution time for performance observation.
 */
@Slf4j
@Aspect
@Component
public class RecordTimeAspect {
    /**
     * Measures and logs method execution time.
     */
    @Around("execution(* org.starry.webmanagement.service.Impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();

        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        log.info("{} Running time: {}", pjp.getSignature(), end - begin);
        return result;
    }
}
