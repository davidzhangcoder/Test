package com.netty.heima.b5_protocol;

import java.io.Serializable;

public abstract class Message implements Serializable {
    public abstract int getType();
}
