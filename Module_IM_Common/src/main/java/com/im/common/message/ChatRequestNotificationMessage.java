package com.im.common.message;

public class ChatRequestNotificationMessage extends ChatRequestMessage{

    public ChatRequestNotificationMessage(long fromID, String from, long toID, String to, String message) {
        super(fromID, from, toID, to, message);
    }

    @Override
    public MessageType getType() {
        return MessageType.ChatRequestNotificationMessage;
    }
}
