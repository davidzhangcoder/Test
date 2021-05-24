package com.netty.heima.b4_stick_and_half_package.stick.solution.length_field_based;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class b_20210510_TestLengthFieldBasedStickPackageClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210510_TestLengthFieldBasedStickPackageClient.class);

    private static final char LINE_DELIMITER = '\n';

    private static final int LENGTH = 30;

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(clientGroup)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    LOGGER.debug("客户端连接已创建");

                                    for (int i = 0; i < 5 ; i++) {
                                        int randomLength = ThreadLocalRandom.current().nextInt(LENGTH)+1;
                                        //int randomLength = 30; //会报 io.netty.handler.codec.TooLongFrameException
                                        int value = ThreadLocalRandom.current().nextInt('A', 'Z');

                                        byte[] bytes = new byte[randomLength];
                                        for (int j = 0; j < randomLength; j++) {
                                            bytes[j] = (byte) value;
                                        }

                                        ByteBuf buffer = ctx.alloc().buffer();
                                        buffer.writeInt(randomLength); //int型是4个字节
                                        buffer.writeBytes(bytes);
                                        //ByteBufferUtil.log(buffer);
                                        ctx.writeAndFlush(buffer);
                                    }
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    LOGGER.error("客户端-exceptionCaught : " , cause);
                                }
                            });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("客户端错误", e.getMessage());
        } finally {
            clientGroup.shutdownGracefully();
        }
    }

}
