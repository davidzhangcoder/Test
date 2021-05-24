package com.netty.heima.b2_TestEventLoopServerClient_version2;

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
import java.util.Scanner;


public class b_20210507_TestEventLoopServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210507_TestEventLoopServer.class);

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 增加自定义的非NioEventLoopGroup
        EventLoopGroup group = new DefaultEventLoopGroup();

        ChannelFuture serverChannel = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                        ch.pipeline().addLast("h1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                String s = byteBuf.toString(Charset.defaultCharset());
//                                System.out.println(Thread.currentThread().getName() + " : " + s);
                                LOGGER.debug(s);
                                super.channelRead(ctx, s);
                            }
                        });

                        ch.pipeline().addLast(group, "h2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                String s = (String) msg;
                                if ("shutdown".equalsIgnoreCase(s)) {
                                    LOGGER.debug(s);
                                }
                            }
                        });
                    }
                })
                .bind(8080);

        LOGGER.debug(".....服务器 is ready...");

        serverChannel.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    LOGGER.debug("监听端口 8080 成功");

                    new Thread(()->{
                        Scanner scan = new Scanner(System.in);
                        Channel channel = serverChannel.channel();
                        while( true ) {
                            // 判断是否还有输入
                            if (scan.hasNextLine()) {
                                String str2 = scan.nextLine();
                                System.out.println("输入的数据为：" + str2);
                                if("q".equalsIgnoreCase(str2)){
                                    LOGGER.debug("准备关闭连接");
                                    channel.close();
                                    scan.close();
                                    break;
                                }
                            }
                        }
                    }, "Input-Thread").start();
                } else {
                    LOGGER.debug("监听端口 8080 失败");
                }
            }
        });

        ChannelFuture closeFuture = serverChannel.channel().closeFuture();
        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    LOGGER.debug("关闭 成功");
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                } else
                    LOGGER.debug("关闭 失败");
            }
        });

    }
}
