package com.im.common.message;

public class ACKChatRequestNotificationMessage extends Message{
    @Override
    public MessageType getType() {
        return MessageType.ACKChatRequestNotificationMessage;
    }
}
