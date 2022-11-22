package com.im.handler;

import com.im.common.message.ClientInitializeRequestMessage;
import com.im.common.redis.RedisUtil;
import com.im.server.ClientSessionManager;
import com.im.server.ServerAttributeKey;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ClientInitialServerHandler extends SimpleChannelInboundHandler<ClientInitializeRequestMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientInitialServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ClientSessionManager.getInstance().increaseCount();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClientInitializeRequestMessage msg) throws Exception {
        String userName = msg.getUserName();
        long userID = msg.getUserID();
        Channel channel = ctx.channel();

        ClientSessionManager.getInstance().addClientChannel(userID, channel);
        RedisUtil.getInstance().setUserOnline( ctx.channel().attr(ServerAttributeKey.CHANNEL_IM_SERVER_NODE).get().getServerKey(),
                userID, true);

        ctx.channel().attr(ServerAttributeKey.CHANNEL_USERID).set(userID);

        LOGGER.info("注册 userID:{}, userName:{} 到 ChannelID: {} , CHANNEL_USERID:{}" ,
                userID, userName, channel.id(), ctx.channel().attr(ServerAttributeKey.CHANNEL_USERID).get());
    }
}
