package com.netty.heima.b10_rpc.server.handler;

import com.netty.heima.b10_rpc.common.message.RPCRequestMessage;
import com.netty.heima.b10_rpc.common.message.RPCResponseMessage;
import com.netty.heima.b10_rpc.common.service.HelloService;
import com.netty.heima.b10_rpc.common.service.ServiceFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

@ChannelHandler.Sharable
public class RPCServerInboundHandler extends SimpleChannelInboundHandler<RPCRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCRequestMessage msg) throws Exception {

        RPCResponseMessage responseMessage = new RPCResponseMessage();
        responseMessage.setSeqID(msg.getSeqID());
        try {
            Class aClass = Class.forName(msg.getClassForName());
            HelloService serviceImpl = (HelloService) ServiceFactory.getServiceImpl(aClass);
            Method method = HelloService.class.getMethod(msg.getMethodName(), msg.getParameterTypes());
            Object result = method.invoke(serviceImpl, msg.getParameters());

            responseMessage.setResult(result);
            responseMessage.setResultType(method.getReturnType());
            responseMessage.setSuccess(true);
        }
        catch(Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setMessage( e.getCause().getMessage() );
        }
        ctx.writeAndFlush(responseMessage);
    }
}
