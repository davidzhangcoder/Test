package com.netty.heima.b6_chat.message;

public class LoginResponseMessage extends AbstractResponseMessage{

    public LoginResponseMessage(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    @Override
    public MessageType getType() {
        return MessageType.LoginResponseMessage;
    }
}
