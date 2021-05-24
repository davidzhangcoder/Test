package com.netty.heima.b10_rpc.service;

public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
