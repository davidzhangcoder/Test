package com.netty.heima.b4_stick_and_half_package.half.solution.length_field_based;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b_20210509_TestLengthFieldBasedHalfPackageServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210509_TestLengthFieldBasedHalfPackageServer.class);

    private static final int LENGTH = 50;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_RCVBUF,30);

            serverBootstrap.childOption(ChannelOption.SO_RCVBUF, 30);
//            serverBootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(10));
            serverBootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(30, 30, 30));

            serverBootstrap.group(bossGroup, workerGroup)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(LENGTH,0,4,0,4));

                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

//                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
//                                @Override
//                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//                                    System.out.println(ctx.channel().config().getOption(ChannelOption.SO_RCVBUF));
//
//                                    ByteBuf byteBuf = (ByteBuf) msg;
//                                    String s = byteBuf.toString(Charset.defaultCharset());
//                                    //System.out.println(s);
//                                }
//                            });
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
