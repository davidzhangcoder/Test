package com.test.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Tester {

    public static void main(String[] args) {
        final Produce produce = new Produce();

        /*
         *基于子类的动态代理
         *  涉及的类: Proxy
         *  提供者: JDK官方
         *如何创建代理对象
         *  使用Proxy类中的newProxyInstance方法
         *创建代理对象的要求
         *  被代理类最少实现一个接口，如果没有则不能使用
         *newProxyInstance方法的参数
         *  classLoader: 类加载器
         *      它是用于加载代理对象字节码的，和被代理对象使用相同的类加载器，固定写法
         *  class[]: 字节码数组
         *      它是用于让代理对象和被代理对象有相同方法，固定写法
         *  InvocationHandler: 用于提供增强的代码
         *      它是让我们写如何代理，我们一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的
         *      此接口的实现类都是谁用谁写
         */
        IProduce proxyProducer = (IProduce) Proxy.newProxyInstance(produce.getClass().getClassLoader(),
                produce.getClass().getInterfaces(),
                new InvocationHandler() {
                    /*
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * 方法参数的含义
                     * proxy: 代理对象的引用
                     * method: 当前执行的方法
                     * args: 当前执行方法所需的参数
                     * return 和被代理对象方法有相同的返回值
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println(method.getName() + " : " + args);
                        return method.invoke(produce,(Float)(args[0])*0.8f);
                    }
                });

        System.out.println(proxyProducer.produce(10));
    }
}
