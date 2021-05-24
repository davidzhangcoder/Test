package com.netty.connection_pool;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class NettyChannelPoolHandler implements ChannelPoolHandler {

    private static final Logger log = LoggerFactory.getLogger(ChannelTaskThread.class);

    static final ByteBuf byteBuf = Unpooled.copiedBuffer(DataBusConstant.DELIMITER.getBytes());

    @Override
    public void channelReleased(Channel ch){
        ch.writeAndFlush(Unpooled.EMPTY_BUFFER);
        log.info("|-->回收Channel. Channel ID: " + ch.id());
    }

    @Override
    public void channelAcquired(Channel ch){
        log.info("|-->获取Channel. Channel ID: " + ch.id());
    }

    @Override
    public void channelCreated(Channel ch){

        log.info("|-->创建Channel. Channel ID: " + ch.id()
                 +"\r\n|-->创建Channel. Channel REAL HASH: " + System.identityHashCode(ch));
        SocketChannel channel = (SocketChannel) ch;
        channel.config().setKeepAlive(true);
        channel.config().setTcpNoDelay(true);
        channel.pipeline()
                //开启Netty自带的心跳处理器，每5秒发送一次心跳
                .addLast(new IdleStateHandler(0, 0, 5, TimeUnit.SECONDS))
                .addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,byteBuf))
                .addLast(new NettyClientHandler());
    }
}