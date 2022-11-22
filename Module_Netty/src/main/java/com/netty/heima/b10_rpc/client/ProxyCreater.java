package com.netty.heima.b10_rpc.client;

import com.netty.heima.b10_rpc.common.message.RPCRequestMessage;
import io.netty.channel.Channel;
import io.netty.util.concurrent.DefaultPromise;

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
                    long seqID = ResultHolder.getInstance().getSeqID();
                    RPCRequestMessage rpcRequestMessage = new RPCRequestMessage(
                            classForName,
                            methodName,
                            parameterTypes,
                            parameters,
                            seqID);

                    DefaultPromise promise = new DefaultPromise(channel.eventLoop());
                    ResultHolder.getInstance().put(seqID, promise);

                    channel.writeAndFlush(rpcRequestMessage);

                    promise.await();

                    if (promise.isSuccess()) {
                        return promise.getNow();
                    } else {
                        throw new RuntimeException(promise.cause());
                    }
                }
        );
        return (T) o;
    }
}
