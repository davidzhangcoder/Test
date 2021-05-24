package com.netty.heima.b10_rpc.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflection {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
//        Class<?> aClass = Class.forName("com.netty.heima.b10_rpc.service.HelloServiceImpl");
//        Object o = aClass.newInstance();
//        Method method = aClass.getMethod("sayHello", String.class);
//        Object result = method.invoke(o , "zhangsan");
//        System.out.println(result);

//        HelloService serviceImpl = ServiceFactory.getServiceImpl(HelloService.class);
//        Method method = HelloService.class.getMethod("sayHello", String.class);
//        Object result = method.invoke(serviceImpl , "zhangsan");
//        System.out.println(result);

        Class aClass = Class.forName("com.netty.heima.b10_rpc.service.HelloService");
        HelloService serviceImpl = (HelloService) ServiceFactory.getServiceImpl(aClass);
        Method method = HelloService.class.getMethod("sayHello", String.class);
        Object result = method.invoke(serviceImpl , "zhangsan");

        Class<?> returnType = method.getReturnType();
        System.out.println(result);
    }


}
