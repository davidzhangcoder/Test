package com.netty.heima.b3_TestEventLoopServerClient_version3;

import com.netty.heima.b3_TestEventLoopServerClient_version3.template.b_20210509_TestEventLoopClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b_20210517_TestHighWaterMarkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210509_TestEventLoopClient.class);

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(clientGroup)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.channel().config().setWriteBufferLowWaterMark(10);
                                    ctx.channel().config().setWriteBufferHighWaterMark(10);
                                    new Thread(()-> {
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        ChannelOutboundBuffer channelOutboundBuffer = ctx.channel().unsafe().outboundBuffer();
                                        int i=0;
                                        while(true)
//                                        for (int i = 0; i < 50000; i++)
                                        {
                                            System.out.println((i++) + "======");
                                            System.out.println(channelOutboundBuffer.nioBufferSize());
                                            System.out.println(channelOutboundBuffer.nioBufferCount());
                                            System.out.println(channelOutboundBuffer.bytesBeforeUnwritable());
                                            System.out.println(channelOutboundBuffer.bytesBeforeWritable());
                                            System.out.println(ctx.channel().isWritable());
                                            System.out.println("======");

                                            if(ctx.channel().isWritable()) {
                                                ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer();
                                                buffer.writeBytes("hello".getBytes());
                                                //ByteBuf buffer = Unpooled.wrappedBuffer("hello".getBytes());
                                                ctx.writeAndFlush(buffer);
                                            }else{
                                                System.out.println(ctx.channel().unsafe().outboundBuffer().nioBufferCount());
                                            }
                                        }
                                    },"Netty Client").start();
                                }
                            });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            LOGGER.debug("客户端连接已创建");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("客户端错误", e.getMessage());
        } finally {
            clientGroup.shutdownGracefully();
        }

    }

}
