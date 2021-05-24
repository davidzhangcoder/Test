package com.netty.heima.b6_chat.client_handler;

import com.netty.heima.b6_chat.message.ChatRequestMessage;
import com.netty.heima.b6_chat.message.ChatResponseMessage;
import com.netty.heima.b6_chat.message.LoginResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientChannelInboundProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientChannelInboundProcessor.class);

    private Object waiter;

    public ClientChannelInboundProcessor(Object waiter) {
        this.waiter = waiter;
    }

    public void process(Object msg) {
        if( msg instanceof LoginResponseMessage ) {
            LoginResponseMessage loginResponseMessage = (LoginResponseMessage) msg;
            LOGGER.debug("name is {}", loginResponseMessage.getType());
            synchronized (waiter) {
                waiter.notifyAll();
            }
        }
        else if( msg instanceof ChatRequestMessage ) {
            ChatRequestMessage chatRequestMessage = (ChatRequestMessage) msg;
            System.out.println(chatRequestMessage.getFrom() + " 说: " + chatRequestMessage.getMessage());
        }
        else if( msg instanceof ChatResponseMessage ) {
            ChatResponseMessage chatResponseMessage = (ChatResponseMessage) msg;
            LOGGER.debug(chatResponseMessage.getMessage());
        }
    }

}
