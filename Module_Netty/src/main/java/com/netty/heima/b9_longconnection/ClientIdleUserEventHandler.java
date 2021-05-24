package com.netty.heima.b9_longconnection;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ClientIdleUserEventHandler extends ChannelDuplexHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientIdleUserEventHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                LOGGER.debug("发送心跳包");
                ByteBuf byteBuf = ctx.alloc().buffer().writeBytes("HeartBeat".getBytes());
                ctx.writeAndFlush(byteBuf);
            }
        }
    }
}
