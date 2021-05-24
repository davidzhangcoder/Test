package com.netty.heima.b8_http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

public class b_20210518_TestHttpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210518_TestHttpServer.class);

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
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(new HttpServerCodec());

                            ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                                    DefaultHttpRequest defaultHttpRequest = (DefaultHttpRequest) msg;
                                    LOGGER.debug(defaultHttpRequest.getUri());

                                    DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(
                                            defaultHttpRequest.protocolVersion(), HttpResponseStatus.OK);
                                    String content = "<h1>hello, world<h1>";
                                    defaultFullHttpResponse.content().writeBytes(content.getBytes());
                                    defaultFullHttpResponse.headers().setInt(CONTENT_LENGTH,content.length());
                                    ctx.writeAndFlush(defaultFullHttpResponse);
                                }
                            });

                            ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpContent>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, HttpContent msg) throws Exception {
                                    LOGGER.debug(msg.toString());
                                }
                            });
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
