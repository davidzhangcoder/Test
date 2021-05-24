package com.netty.heima.b6_chat.handler;

import com.netty.heima.b6_chat.helper.LoginHelper;
import com.netty.heima.b6_chat.message.LoginRequestMessage;
import com.netty.heima.b6_chat.message.LoginResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        LoginRequestMessage loginRequestMessage = (LoginRequestMessage) msg;
        LOGGER.debug("name is {}, password is {}" , loginRequestMessage.getUserName() , loginRequestMessage.getPassword());

        LoginHelper.getInstance().bind(loginRequestMessage.getUserName(), ctx.channel());

        LoginResponseMessage loginResponseMessage = new LoginResponseMessage(true, "Server");
        ctx.writeAndFlush(loginResponseMessage);
    }
}
