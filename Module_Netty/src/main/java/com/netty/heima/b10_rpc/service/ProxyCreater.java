package com.netty.heima.b10_rpc.service;

import com.netty.heima.b10_rpc.ChannelManager;
import com.netty.heima.b10_rpc.message.RPCRequestMessage;
import io.netty.channel.Channel;

import java.lang.reflect.Proxy;

public class ProxyCreater {

    public static <T> T getProxy(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) -> {
                    String classForName = clazz.getName();
                    //System.out.println(classForName);

                    String methodName = method.getName();
                    //System.out.println(methodName);

                    Class[] parameterTypes = method.getParameterTypes();
                    Object[] parameters = args;

                    Channel channel = ChannelManager.getChannel();
                    RPCRequestMessage rpcRequestMessage = new RPCRequestMessage(
                            classForName,
                            methodName,
                            parameterTypes,
                            parameters);
                    channel.writeAndFlush(rpcRequestMessage);

                    return null;
                }
        );
        return (T) o;
    }
}
