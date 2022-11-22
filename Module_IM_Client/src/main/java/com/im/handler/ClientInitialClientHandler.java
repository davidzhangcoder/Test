package com.im.handler;

import com.im.common.message.ClientInitializeRequestMessage;
import com.im.common.message.ClientInitializeResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ClientInitialClientHandler extends SimpleChannelInboundHandler<ClientInitializeResponseMessage> {

    public final static String NAME = "ClientInitialHandler";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ClientInboundHander clientInboundHander = new ClientInboundHander();
        ctx.pipeline().remove(ClientInitialClientHandler.NAME);
        ctx.pipeline().addLast(clientInboundHander);

        ClientInitializeRequestMessage clientInitializeRequestMessage = new ClientInitializeRequestMessage();
        clientInitializeRequestMessage.setMessage("test initial");
        clientInitializeRequestMessage.setUserID(2);
        clientInitializeRequestMessage.setUserName("b");
        ctx.writeAndFlush(clientInitializeRequestMessage);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClientInitializeResponseMessage msg) throws Exception {
        ctx.pipeline().remove(NAME);
    }
}
