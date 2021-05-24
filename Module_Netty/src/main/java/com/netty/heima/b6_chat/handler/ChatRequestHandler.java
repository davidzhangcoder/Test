package com.netty.heima.b6_chat.handler;

import com.netty.heima.b6_chat.helper.LoginHelper;
import com.netty.heima.b6_chat.message.ChatResponseMessage;
import com.netty.heima.b6_chat.message.ChatRequestMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ChatRequestHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String from = msg.getFrom();
        String to = msg.getTo();

        Channel toChanel = LoginHelper.getInstance().getChannel(to);

        if( toChanel != null ){
            toChanel.writeAndFlush(msg);
        } else {
            ChatResponseMessage chatReponseMessage = new ChatResponseMessage(false,"用户已下线");
            ctx.writeAndFlush(chatReponseMessage);
        }
    }
}
