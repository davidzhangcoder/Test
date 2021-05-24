package com.netty.heima.b10_rpc.message;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private String message;

    public abstract MessageType getType();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
