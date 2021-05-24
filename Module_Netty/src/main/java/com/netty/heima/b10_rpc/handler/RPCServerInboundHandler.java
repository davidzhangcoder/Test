package com.netty.heima.b10_rpc.handler;

import com.netty.heima.b10_rpc.message.RPCRequestMessage;
import com.netty.heima.b10_rpc.message.RPCResponseMessage;
import com.netty.heima.b10_rpc.service.HelloService;
import com.netty.heima.b10_rpc.service.ServiceFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

@ChannelHandler.Sharable
public class RPCServerInboundHandler extends SimpleChannelInboundHandler<RPCRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCRequestMessage msg) throws Exception {

        Class aClass = Class.forName(msg.getClassForName());
        HelloService serviceImpl = (HelloService) ServiceFactory.getServiceImpl(aClass);
        Method method = HelloService.class.getMethod(msg.getMethodName(), msg.getParameterTypes());
        Object result = method.invoke(serviceImpl , msg.getParameters());

        RPCResponseMessage responseMessage = new RPCResponseMessage(true,"");
        responseMessage.setResult(result);
        responseMessage.setResultType(method.getReturnType());
        ctx.writeAndFlush(responseMessage);
    }
}
