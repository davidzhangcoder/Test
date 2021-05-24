package com.test.spring.aop.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AOPConfiguration {

    @Pointcut("execution( * com.test.spring.aop.controller.*.*(..))")
    //修饰符如public等可以不写，但不能为*
    //第一个*在这表示controller下的所有类
    //第二个*表示所有方法
    //..表示多个参数
    public void pointCut() {

    }

    //spring 5
//    方法: AroundNotify前
//    方法执行前
//    方法执行后
//    方法执行后: AfterThrowing

    //spring 5
//    方法: AroundNotify前
//            方法执行前
//    返回值是: Hello : sayHelloNoArg
//    方法: AroundNotify后
//            方法执行后
//    方法执行后: AfterReturning

    @Before("pointCut()")
    public void beforeNotify(JoinPoint joinPoint) {
        System.out.println("方法执行前");
    }

    @After("pointCut()")
    public void afterNotify(JoinPoint joinPoint) {
        System.out.println("方法执行后");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturningNotify(JoinPoint joinPoint, Object result) {
        System.out.println("方法执行后: AfterReturning");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowingNotify() {
        System.out.println("方法执行后: AfterThrowing");
    }

    @Around("pointCut()")
    public Object aroundNotify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("方法: AroundNotify前");

//        Object[] arg = new Object[]{"override arg"};
//        Object result = proceedingJoinPoint.proceed(arg);

        Object result = proceedingJoinPoint.proceed();

        System.out.println("返回值是: " + result);
        System.out.println("方法: AroundNotify后");

        return result;
    }


}
