package org.portfolio.bookingservice.crosscuttingconcerns;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class LoggingAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Around("execution(* org.portfolio.bookingservice.business.concretes.BookingManager.*(..))")
    public Object LogAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature= (MethodSignature) proceedingJoinPoint.getSignature();
        logger.info(methodSignature.getMethod()+" has been started with argument/arguments { "+extractParamsAsString(proceedingJoinPoint)+"}");
        Object result;
        try {
            result=proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        }catch (Exception e)
        {
            logger.error(methodSignature.getMethod()+" caused exception of "+e.getMessage());
            throw e;
        }
        if(result!=null) {
            logger.info(methodSignature.getMethod()+" completed successfully with input of { "+extractParamsAsString(proceedingJoinPoint)+"} and result of {"+result+"}");
        }else {
            logger.info(methodSignature.getMethod()+" completed successfully with input of { "+extractParamsAsString(proceedingJoinPoint)+"} and either return type was void or returned value is null");
        }
        return null;
    }

    public String extractParamsAsString(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        if(signature.getMethod().getParameterCount()==0)
            return null;

        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            sb.append(parameterNames[i]).append(": ").append(parameterValues[i]);
            if (i < parameterNames.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
