package com.im.handler;

import com.im.common.message.ChatRequestMessage;
import com.im.common.message.ChatResponseMessage;
import com.im.common.message.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ClientInboundHander extends SimpleChannelInboundHandler<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientInboundHander.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        if (msg instanceof ChatRequestMessage) {
            ChatRequestMessage chatRequestMessage = (ChatRequestMessage) msg;
            LOGGER.info("从UserID:{} 收到 消息:{}" , chatRequestMessage.getFrom() , chatRequestMessage.getMessage());
        }
        else if (msg instanceof ChatResponseMessage) {
            ChatResponseMessage chatResponseMessage = (ChatResponseMessage) msg;
            LOGGER.info("收到 ChatResponseMessage 消息:{} 来自服务器 (说明服务器已收到消息)" , chatResponseMessage.getMessage());
        }
    }
}
