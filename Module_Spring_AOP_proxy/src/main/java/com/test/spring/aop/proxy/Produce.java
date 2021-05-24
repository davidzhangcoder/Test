package com.test.spring.aop.proxy;

public class Produce implements IProduce{
    public String produce(float price) {
        return "生产1件，价格是: " + price;
    }

    public String customerService(float price) {
        return "售后服务，价格是: " + price;
    }
}
