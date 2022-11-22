package com.im.handler.runnable;

import com.im.common.configuration.IMServerNode;
import com.im.common.message.ChatRequestMessage;
import com.im.common.message.ChatRequestNotificationMessage;
import com.im.common.message.Message;
import com.im.common.redis.RedisUtil;
import com.im.server.ClientSessionManager;
import com.im.server.IMServerClusterServerSide;
import io.netty.channel.Channel;

import java.util.List;

public class HandleChatRequestMessageRunnable implements Runnable{
    private final Message msg;

    public HandleChatRequestMessageRunnable(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        ChatRequestMessage chatRequestMessage = (ChatRequestMessage) msg;

        long fromUserID = chatRequestMessage.getFromID();
        long toUserID = chatRequestMessage.getToID();

        Channel toUserChannel = ClientSessionManager.getInstance().getClientChannel(toUserID);
        if( toUserChannel == null ) {
            //send message to peer server
            List<IMServerNode> allIMServers = IMServerClusterServerSide.getInstance().getAllIMServers();
            for (IMServerNode imServerNode : allIMServers) {
                if(RedisUtil.getInstance().isUserOnline(imServerNode.getServerKey(),toUserID)) {
                    ChatRequestNotificationMessage chatRequestNotificationMessage
                            = new ChatRequestNotificationMessage(chatRequestMessage.getFromID(),chatRequestMessage.getFrom(),
                            chatRequestMessage.getToID(),chatRequestMessage.getTo(),chatRequestMessage.getMessage());
                    IMServerClusterServerSide.getInstance().getPeerNodeChannel(imServerNode).writeAndFlush(chatRequestNotificationMessage);
                }
            }
        } else {
            //or send to channel
            toUserChannel.writeAndFlush(msg);
        }
    }
}
