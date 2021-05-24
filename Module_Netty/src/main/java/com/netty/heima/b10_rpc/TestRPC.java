package com.netty.heima.b10_rpc;

import com.netty.heima.b10_rpc.service.HelloService;
import com.netty.heima.b10_rpc.service.ProxyCreater;

public class TestRPC {

    public static void main(String[] args) {
//        Channel channel = ChannelManager.getChannel();
//
//        RPCRequestMessage rpcRequestMessage = new RPCRequestMessage(
//                "com.netty.heima.b10_rpc.service.HelloService",
//                "sayHello",
//                new Class[]{String.class},
//                new Object[]{"zhangsan"});
//        channel.writeAndFlush(rpcRequestMessage);

        HelloService proxy = ProxyCreater.getProxy(HelloService.class);
        proxy.sayHello("jack");
    }

}

//rpc
//源码
//futuretask
//电子书
//IMSDK
//单体SDK
//distributed SDK
//spide
