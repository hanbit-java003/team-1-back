package com.hanbit.cock.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class EmblemAspecet {
	
	private static final Logger logger = LoggerFactory.getLogger(EmblemAspecet.class);
	
	@Pointcut("@annotation(com.hanbit.cock.api.annotation.EmblemUpdate)")
	public void emblemServiceMethod() {
	
	}
	
	@AfterReturning(pointcut = "emblemServiceMethod()", returning = "retVal") 
	public void printString(JoinPoint joinPoint, Object retVal ) {
		String methodName = joinPoint.getSignature().toShortString();
		System.out.println(methodName + "method name");
	}
}
