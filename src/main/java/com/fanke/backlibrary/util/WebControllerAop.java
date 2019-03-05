package com.fanke.backlibrary.util;

/**
 * Created by Administrator on 2018/3/28.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebControllerAop {
    Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.fanke.backlibrary.service..*.*(..))")
    public void pointcut(){}
    /**
     * 前置增强
     * @param jp
     */
    @Before("pointcut()")
    public void before(JoinPoint jp) {
        LOGGER.info("调用 " + jp.getTarget() + " 的 " + jp.getSignature().getName()
                + " 方法。方法入参：" + Arrays.toString(jp.getArgs()));
    }

    /**
     * 后置增强
     * @param jp
     * @param returnValue
     */
    @AfterReturning(pointcut = "pointcut()", returning = "returnValue")
    public void afterReturning(JoinPoint jp, Object returnValue) {
        LOGGER.info("调用 " + jp.getTarget() + " 的 " + jp.getSignature().getName()
                + " 方法。方法返回值：" + returnValue);
    }


    /**
     * 最终增强
     * @param jp
     */
    @After("pointcut()")
    public void afterLogger(JoinPoint jp) {
        LOGGER.info(jp.getSignature().getName() + " 方法结束执行。");
    }

    /**
     * 异常增强
     * @param jp
     * @param e
     */
    @AfterThrowing(value="pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint jp, RuntimeException e) {
        LOGGER.error(jp.getSignature().getName() + " 方法发生异常：" + e);
    }

    /**
     * 环绕增强
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable {
        LOGGER.info("调用 " + jp.getTarget() + " 的 " + jp.getSignature().getName()
                + " 方法。方法入参：" + Arrays.toString(jp.getArgs()));
        try {
            Object result = jp.proceed();
            LOGGER.info("调用 " + jp.getTarget() + " 的 "
                    + jp.getSignature().getName() + " 方法。方法返回值：" + result);
            return result;
        } catch (Throwable e) {
            LOGGER.error(jp.getSignature().getName() + " 方法发生异常：" + e);
            throw e;
        } finally {
            LOGGER.info(jp.getSignature().getName() + " 方法结束执行。");
        }
    }


}