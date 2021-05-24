package com.netty.heima.b10_rpc.clienthandler;

import com.netty.heima.b10_rpc.message.RPCResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class RPCClientInboundHandler extends SimpleChannelInboundHandler<RPCResponseMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCClientInboundHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCResponseMessage msg) throws Exception {
        LOGGER.debug(msg.getResult().toString());
    }
}
