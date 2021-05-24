package com.netty.heima.b1_TestEventLoopServerClient_version1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;


public class b_20210505_TestEventLoopServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210505_TestEventLoopServer.class);

    public static void main(String[] args) throws InterruptedException {
        ChannelFuture serverChannel = new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                String s = byteBuf.toString(Charset.defaultCharset());
//                                System.out.println(Thread.currentThread().getName() + " : " + s);
                                LOGGER.debug(s);
                            }
                        });
                    }
                })
                .bind(8080);

        System.out.println(".....服务器 is ready...");

        serverChannel.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("监听端口 8080 成功");
                } else {
                    System.out.println("监听端口 8080 失败");
                }
            }
        });
    }
}
