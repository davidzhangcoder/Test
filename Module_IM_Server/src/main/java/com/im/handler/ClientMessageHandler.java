package com.im.handler;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.im.common.configuration.IMServerNode;
import com.im.common.message.*;
import com.im.common.redis.RedisUtil;
import com.im.handler.runnable.HandleChatRequestMessageRunnable;
import com.im.server.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class ClientMessageHandler extends SimpleChannelInboundHandler<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientMessageHandler.class);

//    sender:
//    send ChatRequestMessage
//    receive ChatResponseMessage
//    receive ACKChatRequestNotificationMessage

//    server1:
//    receive ChatRequestMessage
//    send ChatResponseMessage to sender
//    send ACKChatRequestNotificationMessage to sender
//    send ChatRequestNotificationMessage to server2 OR receiver
//    receive ACKChatRequestMessage from server2 OR receiver
//    send ACKChatResponseMessage to server2 OR receiver

//    server2:
//    receive ChatRequestNotificationMessage
//    send ACKChatRequestMessage to server1
//    receive ACKChatResponseMessage

//    receiver:
//    receive ChatRequestNotificationMessage
//    send ACKChatRequestMessage to server1
//    receive ACKChatResponseMessage

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        if(msg instanceof ChatRequestMessage) {
            LOGGER.info("处理 ChatRequestMessage");
            forwardChatRequestNotificationMessage(ctx, msg , 0);

            replayChatResponseMessage(ctx);
        }
        else if(msg instanceof ChatRequestNotificationMessage){
            LOGGER.info("处理 ChatRequestNotificationMessage");
            //get toUser Channel and send
            ChatRequestNotificationMessage chatRequestNotificationMessage = (ChatRequestNotificationMessage) msg;
            long fromUserID = chatRequestNotificationMessage.getFromID();
            long toUserID = chatRequestNotificationMessage.getToID();

            Channel toUserChannel = ClientSessionManager.getInstance().getClientChannel(toUserID);
            if( toUserChannel != null )
                toUserChannel.writeAndFlush(msg);

            //return ACKChatRequestMessage
            ACKChatRequestMessage ackChatRequestMessage = new ACKChatRequestMessage(
                    chatRequestNotificationMessage.getFromID(),
                    chatRequestNotificationMessage.getFrom(),
                    chatRequestNotificationMessage.getToID(),
                    chatRequestNotificationMessage.getTo(),
                    chatRequestNotificationMessage.getMessage());
            ctx.writeAndFlush(ackChatRequestMessage);
        }
        else if(msg instanceof ACKChatRequestMessage){
            LOGGER.info("处理 ACKChatRequestMessage");
            //todo
            //stop resend "forwardChatRequestNotificationMessage"

            //return ACKChatResponseMessage
            ACKChatResponseMessage ackChatResponseMessage = new ACKChatResponseMessage();
            ctx.writeAndFlush(ackChatResponseMessage);

            ACKChatRequestMessage ackChatRequestMessage = (ACKChatRequestMessage) msg;
            long fromUserID = ackChatRequestMessage.getFromID();
            String serverKey = ctx.channel().attr(ServerAttributeKey.CHANNEL_IM_SERVER_NODE).get().getServerKey();
            if(RedisUtil.getInstance().isUserOnline(serverKey,fromUserID)){
                //send ACKChatRequestNotificationMessage

                ACKChatRequestNotificationMessage ackChatRequestNotificationMessage = new ACKChatRequestNotificationMessage();
                Channel clientChannel = ClientSessionManager.getInstance().getClientChannel(fromUserID);
                clientChannel.writeAndFlush(ackChatRequestNotificationMessage);
            } else {
                //forward ACKChatRequestMessage
                List<IMServerNode> allIMServers = IMServerClusterServerSide.getInstance().getAllIMServers();
                for (IMServerNode imServerNode : allIMServers) {
                    //send message to peer server
                    if(RedisUtil.getInstance().isUserOnline(imServerNode.getServerKey(),fromUserID))
                        IMServerClusterServerSide.getInstance().getPeerNodeChannel(imServerNode).writeAndFlush(msg);
                }
            }
        }
        else if( msg instanceof ACKChatResponseMessage) {
            LOGGER.info("处理 ACKChatResponseMessage");
        }
    }

    private void replayChatResponseMessage(ChannelHandlerContext ctx){
        ChatResponseMessage chatResponseMessage = new ChatResponseMessage(true,"");
        ScheduledExecutorService executor = IMServerExecutors.getInstance().getExecutor(ctx.channel().eventLoop());
        executor.submit(()-> ctx.writeAndFlush(chatResponseMessage));
    }

    private void forwardChatRequestNotificationMessage(ChannelHandlerContext ctx, Message msg , int count) {
        //send message to peer server
        //or send to channel
        //waiting for response

        ScheduledExecutorService executor = IMServerExecutors.getInstance().getExecutor(ctx.channel().eventLoop());
        ListenableFutureTask future = ListenableFutureTask.create(new HandleChatRequestMessageRunnable(msg),null);

        future.addListener( ()->{
            //wait a configured period like 2 second, if no response message return, send again
            executor.schedule(()-> {
                //if not get Acknowledge response, resend it
                //todo: count should configurable
                if (true) {
                    if (count < 3) {
                        forwardChatRequestNotificationMessage(ctx, msg, count + 1);
                    } else {
                        //提示："网络问题，稍后再试"
                    }
                } else {

                }
            }, 2 , TimeUnit.SECONDS);
        } , executor);

        executor.submit( future );
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Long userID = ctx.channel().attr(ServerAttributeKey.CHANNEL_USERID).get();

        ClientSessionManager.getInstance().removeClientChannel(userID);
        ClientSessionManager.getInstance().decreaseCount();
        RedisUtil.getInstance().setUserOnline( ctx.channel().attr(ServerAttributeKey.CHANNEL_IM_SERVER_NODE).get().getServerKey(),
                userID, false);
    }

}
