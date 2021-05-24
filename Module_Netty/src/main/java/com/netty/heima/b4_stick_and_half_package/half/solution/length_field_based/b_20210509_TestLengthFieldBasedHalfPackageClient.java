package com.netty.heima.b4_stick_and_half_package.half.solution.length_field_based;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b_20210509_TestLengthFieldBasedHalfPackageClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210509_TestLengthFieldBasedHalfPackageClient.class);

    private static final int LENGTH = 50;

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(clientGroup)
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

//                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
//                                @Override
//                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                    LOGGER.debug("客户端连接已创建");
//
//                                    for (int i = 0; i < 5 ; i++) {
//                                        int randomLength = ThreadLocalRandom.current().nextInt(LENGTH)+1;
//                                        //int randomLength = 30; //会报 io.netty.handler.codec.TooLongFrameException
//                                        int value = ThreadLocalRandom.current().nextInt('A', 'Z');
//
//                                        byte[] bytes = new byte[randomLength];
//                                        for (int j = 0; j < randomLength; j++) {
//                                            bytes[j] = (byte) value;
//                                        }
//
//                                        ByteBuf buffer = ctx.alloc().buffer();
//                                        buffer.writeInt(randomLength); //int型是4个字节
//                                        buffer.writeBytes(bytes);
//                                        //ByteBufferUtil.log(buffer);
//                                        ctx.writeAndFlush(buffer);
//                                    }
//                                }
//
//                                @Override
//                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//                                    LOGGER.error("客户端-exceptionCaught : " , cause);
//                                }
//                            });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("192.168.1.6", 8080);
            channelFuture.sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("客户端错误", e.getMessage());
        } finally {
            clientGroup.shutdownGracefully();
        }
    }

}
