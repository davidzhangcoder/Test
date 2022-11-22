package com.im.register;

import com.im.common.configuration.PropertiesLoader;
import com.im.common.configuration.RegisterType;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class Register implements IRegister{

    private static final Logger LOGGER = LoggerFactory.getLogger(Register.class);

    public static IRegister getRegister() {

        // 扫描clazz所在的包
        Reflections reflections = new Reflections(Register.class.getPackage().getName());

        // 获取clazz所在的包下面所有实现类
        Set<Class<? extends Register>> implClass = reflections.getSubTypesOf(Register.class);

        if( implClass != null && implClass.size() > 0 ) {
            RegisterType registerType = PropertiesLoader.getRegisterType();
            for (Class<? extends Register> aClass : implClass) {
                Register register = null;
                try {
                    register = aClass.newInstance();
                } catch (InstantiationException |  IllegalAccessException e) {
                    LOGGER.error(e.getMessage());
                    throw new RuntimeException("生成Register错误");
                }
                if (register.getRegisterType().equals(registerType)) {
                    return register;
                }

            }
        }

        throw new RuntimeException("没有配置的RegisterType的相应Register实现,请修改RegisterType配置");
    }
}
