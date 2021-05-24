package com.test.spring.aop.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("com.test.spring.aop.controller");
        context.refresh();

        System.out.println(context.getBean(ComponentA.class));
    }
}
