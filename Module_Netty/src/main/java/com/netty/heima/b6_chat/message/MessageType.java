package com.netty.heima.b6_chat.message;

public enum MessageType {

    LoginRequestMessage(1),
    LoginResponseMessage(2),
    ChatRequestMessage(3),
    ChatResponseMessage(4);

    private final long id;

    private MessageType(long id){
        this.id = id;
    }

}
