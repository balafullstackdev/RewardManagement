package com.rewards.monitor.aspect.logs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationAspectLogger {

	private Logger LGGR = LoggerFactory.getLogger(this.getClass());

	@Pointcut
	public void rewardsApplicationLoggerPointcut() {
	}

	@Around(value = "execution (* com.rewards..*(..))")
	public Object arounAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		LGGR.info(className + "." + methodName + " executed in " + executionTime + "ms");
		return proceed;

	}

}
