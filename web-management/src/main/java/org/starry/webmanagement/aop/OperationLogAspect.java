package org.starry.webmanagement.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.starry.webmanagement.mapper.OperateLogMapper;
import org.starry.webmanagement.pojo.OperateLog;
import org.starry.webmanagement.utils.CurrentHolder;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Aspect that records operation logs for methods annotated with {@link org.starry.webmanagement.anno.Log}.
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * Records request operation details after the annotated method completes successfully.
     */
    @Around("@annotation(org.starry.webmanagement.anno.Log)")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        long costTime = end - start;

        OperateLog olog = new OperateLog(
            null,
            getCurrentEmpId(),
            LocalDateTime.now(),
            pjp.getTarget().getClass().getName(),
            pjp.getSignature().getName(),
            Arrays.toString(pjp.getArgs()),
            result != null ? result.toString() : "void",
            costTime
        );

        log.info("recording: {}", olog);
        operateLogMapper.insert(olog);

        return result;
    }

    private Integer getCurrentEmpId() {
        return CurrentHolder.getCurrentId();
    }
}