package com.test.spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Tester {

    public static void main(String[] args) {
        final Produce produce = new Produce();

        /*
         *基于子类的动态代理
         *  涉及的类: Enhancer
         *  提供者: 第三方cglib库
         *如何创建代理对象
         *  使用Enhancer类中的create方法
         *创建代理对象的要求
         *  被代理类不能是最终类
         *create方法的参数
         *  class: 字节码
         *      它是用于指定被代理对象的字节码
         *  callback: 用于提供增强的代码
         *      它是让我们写如何代理，我们一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的
         *      此接口的实现类都是谁用谁写
         *      我们一般写的都是该接口的子接口实现类, MethodInterceptor
         */
        Produce cglibProduce = (Produce) Enhancer.create(produce.getClass(),
                new MethodInterceptor() {
                    /*
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * 方法参数的含义
                     * proxy: 代理对象的引用
                     * method: 当前执行的方法
                     * args: 当前执行方法所需的参数
                     *      以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
                     * methodProxy: 当前执行方法的代理对象
                     * return 和被代理对象方法有相同的返回值
                     */
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return method.invoke(produce,(Float)(args[0])*0.8f);
                    }
                }
        );

        System.out.println(cglibProduce.produce(10));
    }
}
