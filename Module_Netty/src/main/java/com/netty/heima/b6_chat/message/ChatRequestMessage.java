package com.netty.heima.b6_chat.message;

public class ChatRequestMessage extends Message{

    private String from;

    private String to;

    public ChatRequestMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.setMessage(message);
    }

    @Override
    public MessageType getType() {
        return MessageType.ChatRequestMessage;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
