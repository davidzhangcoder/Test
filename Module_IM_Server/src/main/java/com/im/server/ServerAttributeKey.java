package com.im.server;

import com.im.common.configuration.IMServerNode;
import io.netty.util.AttributeKey;

public class ServerAttributeKey {

    public static AttributeKey<Long> CHANNEL_USERID = AttributeKey.valueOf("CHANNEL_USERID");

    public static AttributeKey<IMServerNode> CHANNEL_IM_SERVER_NODE = AttributeKey.valueOf("CHANNEL_IM_SERVER_NODE");
}
