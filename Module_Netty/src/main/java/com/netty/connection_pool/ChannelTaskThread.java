package com.netty.connection_pool;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class ChannelTaskThread implements Callable<String> {

    private static final Logger log = LoggerFactory.getLogger(ChannelTaskThread.class);


    /**
     * netty channel池
     */
    final NettyClientPool nettyClientPool = NettyClientPool.getInstance();

    private String message;

    public ChannelTaskThread(String message){
        this.message = message;
    }

    @Override
    public String call(){
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //同一个线程使用同一个全局唯一的随机数，保证从同一个池中获取和释放资源，同时使用改随机数作为Key获取返回值，时间戳+6位随机数
        long random = Long.valueOf(sdf.format(new Date())) * 1000000 + Math.round(Math.random() * 1000000);

        Channel channel = nettyClientPool.getChannel(random);
        log.debug("在链接池池中取到的Channel： "+ channel.id());

        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf buffer = allocator.buffer(20);
			//使用固定分隔符的半包解码器
        String msg = message + DataBusConstant.DELIMITER;
        buffer.writeBytes(msg.getBytes());
        NettyClientHandler tcpClientHandler = channel.pipeline().get(NettyClientHandler.class);
        ChannelId id = channel.id();
        log.info("SEND SEQNO[{}] MESSAGE AND CHANNEL id [{}]",random,id);

        String serverMsg = tcpClientHandler.sendMessage(buffer, channel);
        NettyClientPool.release(channel);
        return "请求SEQNO["+random+"] "+ serverMsg;
    }
}