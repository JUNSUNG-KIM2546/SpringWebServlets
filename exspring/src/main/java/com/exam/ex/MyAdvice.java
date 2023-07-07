package com.exam.ex;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
//@Aspect //스프링 AOP설정을 담고 있는 클래스임을 표시
public class MyAdvice {
	@Pointcut("execution(* com.exam.ex.My*.*(..))")
    public void targetMethod() {
        // pointcut annotation 값을 참조하기 위한 dummy method
    }
	
	@Before("targetMethod()")
	public void beforeTargetMethod(JoinPoint thisJoinPoint) {
        System.out.println("MyAdvice.beforeTargetMethod executed.");
        // JoinPoint : 현재 이 코드가 끼어들어가 (실행되고) 있는 지점
//        Class clazz = thisJoinPoint.getTarget().getClass();
        
        // 끼어들어간 대상의 클래스명과 메서드명 정보
        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = thisJoinPoint.getSignature().getName();
 
        System.out.println(className + "." + methodName + " executed.");
    }
}
