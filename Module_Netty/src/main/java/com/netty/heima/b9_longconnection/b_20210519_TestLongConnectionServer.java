package com.netty.heima.b9_longconnection;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b_20210519_TestLongConnectionServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210519_TestLongConnectionServer.class);

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .channel(NioServerSocketChannel.class)
                    .group(bossGroup, workerGroup)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(10,0,0));

                            ch.pipeline().addLast(new IdleUserEventHandler());

                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                            ch.pipeline().addLast(new QuitHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            LOGGER.debug("服务器已启动");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("server error", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
