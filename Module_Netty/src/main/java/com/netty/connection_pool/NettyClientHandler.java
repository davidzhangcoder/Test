package com.netty.connection_pool;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ChannelInboundHandlerAdapter.class);

    /**
     * 使用阻塞式LinkedBlockingQueue，对响应结果保存
     * 用于记录通道响应的结果集
     */
    private static final Map<Long, LinkedBlockingQueue<String>> RESULT_MAP = new ConcurrentHashMap<>();

    public String sendMessage(ByteBuf message, Channel ch) {
        LinkedBlockingQueue<String> linked = new LinkedBlockingQueue<>(1);
        //获取channel中存储的全局唯一随机值
        Long randomId = ch.attr(AttributeKey.<Long>valueOf(DataBusConstant.RANDOM_KEY)).get();
        RESULT_MAP.put(randomId,linked);
        ch.writeAndFlush(message);
        String res = null;
        try {
            //设置3分钟的获取超时时间或者使用take()--获取不到返回结果一直阻塞
            res = RESULT_MAP.get(randomId).poll(3, TimeUnit.MINUTES);
            RESULT_MAP.remove(randomId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        String message = null;
        if(msg instanceof String){
            message = msg.toString();
        }else if(msg instanceof ByteBuf){
            message = ((ByteBuf)msg).toString(Charset.defaultCharset());
        }
        //获取channel中存储的全局唯一随机值
        Long randomId = ctx.channel().attr(AttributeKey.<Long>valueOf(DataBusConstant.RANDOM_KEY)).get();
        log.info(" READ INFO 服务端返回结果:"+ message);
        LinkedBlockingQueue<String> linked = RESULT_MAP.get(randomId);
        linked.add(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        boolean active = ctx.channel().isActive();
        log.debug("[此时通道状态] {}", active);
    }

}