package com.netty.heima.b10_rpc;

import com.netty.heima.b10_rpc.client.ChannelManager;
import com.netty.heima.b10_rpc.common.service.HelloService;
import com.netty.heima.b10_rpc.client.ProxyCreater;

public class TestRPC {

    public static void main(String[] args) {
//        Channel channel = ChannelManager.getChannel();
//
//        RPCRequestMessage rpcRequestMessage = new RPCRequestMessage(
//                "com.netty.heima.b10_rpc.common.service.HelloService",
//                "sayHello",
//                new Class[]{String.class},
//                new Object[]{"zhangsan"});
//        channel.writeAndFlush(rpcRequestMessage);

        HelloService proxy = ProxyCreater.getProxy(HelloService.class);
        System.out.println("the result is : " + proxy.sayHello("jack"));

        ChannelManager.getChannel().close();
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
