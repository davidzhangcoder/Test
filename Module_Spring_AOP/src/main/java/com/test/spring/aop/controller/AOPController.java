package com.test.spring.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class AOPController {

    public AOPController() {
        System.out.println("AOPController");
    }

    @GetMapping("sayHello")
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @GetMapping("sayHelloNoArg")
    public String sayHelloNoArg() {
        return "Hello : sayHelloNoArg";
    }

    @GetMapping("hasError")
    public void hasError() {
        int a = 10/0;
    }


}
