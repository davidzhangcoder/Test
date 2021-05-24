package com.netty.heima.b10_rpc.service;

import org.reflections.Reflections;

import java.util.Set;

public class ServiceFactory {

    public static <T> T getServiceImpl(Class<T> clazz) throws IllegalAccessException, InstantiationException {

        // 扫描clazz所在的包
        Reflections reflections = new Reflections(clazz.getPackage().getName());

        // 获取clazz所在的包下面所有实现类
        Set<Class<? extends T>> implClass = reflections.getSubTypesOf(clazz);

        if( implClass != null && implClass.size() > 0 )
            return (T) implClass.iterator().next().newInstance();

        return null;
    }

//    // 存储接口IDataValidator所有实现类的对象
//    private static List<IDataValidator> dataValidators = new ArrayList<>();
//
//    public static List<IDataValidator> getDataValidators() {
//        // 扫描IDataValidator所在的包 com.lm.validate
//        Reflections.reflections = new Reflections(IDataValidator.class.getPackage().getName());
//        // 获取包com.lm.validate下面所有IDataValidator实现类
//        Set<Class<? extends IDataValidator>> implClass = reflections.getSubTypesOf(IDataValidator.class);
//        for (Class<? extends IDataValidator> aClass : implClass) {
//            dataValidators.add(aClass.newInstance());
//        }
//    }

}
