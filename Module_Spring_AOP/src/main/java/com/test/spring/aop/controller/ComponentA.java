package com.test.spring.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentA {

    @Autowired
    private ComponentB componentB;
}
