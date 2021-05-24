package com.netty.heima.b4_stick_and_half_package.stick.solution.fixed_length;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b_20210510_TestFixedLengthStickPackageServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210510_TestFixedLengthStickPackageServer.class);

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .channel(NioServerSocketChannel.class)
                    .group(bossGroup, workerGroup)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(16));
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

//                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
//                                @Override
//                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                    ByteBuf byteBuf = (ByteBuf) msg;
//                                    String s = byteBuf.toString(Charset.defaultCharset());
//                                    System.out.println(s);
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
