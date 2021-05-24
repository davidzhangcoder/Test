package com.netty.heima.b5_protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageClient.class);

    public static void main(String[] args) {
        NioEventLoopGroup client = new NioEventLoopGroup();

        LoggingHandler loggingHandler = new LoggingHandler();
        MessageCodecShareable messageCodec = new MessageCodecShareable();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024,8,4,0,0));
                    ch.pipeline().addLast(loggingHandler);
                    ch.pipeline().addLast(messageCodec);
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            LOGGER.debug("Channel 已激活");

                            Message message = new LoginRequestMessage("jack");
                            ctx.writeAndFlush(message);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            LoginRequestMessage loginRequestMessage = (LoginRequestMessage) msg;
                            LOGGER.debug("name is {}" , loginRequestMessage.getUserName());
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                            LOGGER.debug("Read已完成");
                            ctx.channel().close();
                        }
                    });
                }
            });
            ChannelFuture chanelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            LOGGER.debug("连接已建立");
            chanelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.debug("客户端错误");
        } finally {
            client.shutdownGracefully();
            LOGGER.debug("连接已关闭");
        }
    }

}
