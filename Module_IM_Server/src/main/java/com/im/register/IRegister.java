package com.im.register;

import com.im.common.configuration.IMServerNode;
import com.im.common.configuration.RegisterType;

import java.util.List;

public interface IRegister {
    public void register(IMServerNode imServerNode);

    public RegisterType getRegisterType();

    public void close();

    List<String> getRegisterServer();
}
