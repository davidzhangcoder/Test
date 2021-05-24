package com.example.demo.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/param")
@ConfigurationProperties(prefix = "person")
public class ApplicationPropertiesParameter1Controller {

    private String name;

    private int age;

    private String address;

    @RequestMapping( value = "/getParametersUsingConfigurationProperties" )
    public String getParametersUsingConfigurationProperties() {
        return name + " : " + age + " : " + address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
