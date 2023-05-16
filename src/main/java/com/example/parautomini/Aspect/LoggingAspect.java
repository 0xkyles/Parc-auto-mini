package com.example.parautomini.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Configuration
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.parautomini.Controllers.*.*(..))")
    public void springBeanPointcut() {}

    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() :e.getMessage()!=null ? e.getMessage(): "NULL");
    }

    @Around("springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter: {}.{}() with argument(s) = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }

        try{
            Object result = joinPoint.proceed();
            if (logger.isDebugEnabled()) {
                logger.debug("Exit: {}.{}() with status code = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), ((ResponseEntity)result).getStatusCode());
            }
            return result;
        }catch(IllegalArgumentException e){
            logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }
}
