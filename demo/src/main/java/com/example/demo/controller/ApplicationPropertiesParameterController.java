package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/param")
public class ApplicationPropertiesParameterController {

    @Value("${person.name}")
    private String name1;

    @Value("${person.age}")
    private int age;

    @RequestMapping( value = "/getParametersFromProopFile" )
    public String getParametersFromProopFile() {
        return name1 + " : " + age ;
    }

}
