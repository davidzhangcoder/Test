package com.netty.heima.b9_longconnection;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdleUserEventHandler extends ChannelDuplexHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleUserEventHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                LOGGER.debug("通道无响应，关闭通道 {}" , ctx.channel().id());
                ctx.channel().close();
            }
        }
    }
}
