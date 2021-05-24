package com.netty.heima.b5_protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new LoggingHandler(LogLevel.DEBUG),
                new LengthFieldBasedFrameDecoder(1024,8,4,0,0),
                new MessageCodecShareable()
        );

//        Message message = new LoginRequestMessage("jack");
//        embeddedChannel.writeOutbound(message);


//        Message message = new LoginRequestMessage("jack");
//        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
//        new MessageCodec().encode(null,message, buffer);
//        embeddedChannel.writeInbound(buffer);

        Message message = new LoginRequestMessage("jack");
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,message, buffer);
        ByteBuf slice1 = buffer.slice(0, 30);
        ByteBuf slice2 = buffer.slice(30, buffer.readableBytes()-30);
        slice1.retain();
        slice2.retain();

        embeddedChannel.writeInbound(slice1);
        embeddedChannel.writeInbound(slice2);
    }
}
