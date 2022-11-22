package com.netty.heima.b10_rpc.common.message;

public enum MessageType {

    LoginRequestMessage(1),
    LoginResponseMessage(2),
    ChatRequestMessage(3),
    ChatResponseMessage(4),

    RPCRequestMessage(5),
    RPCResponseMessage(6);

    private final long id;

    private MessageType(long id){
        this.id = id;
    }

}
