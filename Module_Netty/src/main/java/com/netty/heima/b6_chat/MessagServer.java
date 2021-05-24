package com.netty.heima.b6_chat;

import com.netty.heima.b6_chat.handler.ChatRequestHandler;
import com.netty.heima.b6_chat.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagServer.class);

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        LoggingHandler loggingHandler = new LoggingHandler();
        MessageCodecShareable messageCodec = new MessageCodecShareable();
        LoginRequestHandler loginRequestHandler = new LoginRequestHandler();
        ChatRequestHandler chatRequestHandler = new ChatRequestHandler();

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.refCnt();
        buffer.release();
        buffer.refCnt();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024,8,4,0,0));
                    //ch.pipeline().addLast(loggingHandler);
                    ch.pipeline().addLast(messageCodec);
                    ch.pipeline().addLast(loginRequestHandler);
                    ch.pipeline().addLast(chatRequestHandler);
                }
            });

            ChannelFuture chanelFuture = serverBootstrap.bind(8080).sync();
            LOGGER.debug("服务器已启动");
            chanelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("server error", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
