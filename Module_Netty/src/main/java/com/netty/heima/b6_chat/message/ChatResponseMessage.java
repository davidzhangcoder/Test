package com.netty.heima.b6_chat.message;

public class ChatResponseMessage extends AbstractResponseMessage{

    public ChatResponseMessage(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    @Override
    public MessageType getType() {
        return MessageType.ChatResponseMessage;
    }
}
