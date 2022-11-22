package com.im.common.message;

public class ACKChatResponseMessage extends Message{
    @Override
    public MessageType getType() {
        return MessageType.ACKChatResponseMessage;
    }
}
