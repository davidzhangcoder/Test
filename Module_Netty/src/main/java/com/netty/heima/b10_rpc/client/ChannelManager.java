package com.netty.heima.b10_rpc.client;

import com.netty.heima.b10_rpc.client.clienthandler.RPCClientInboundHandler;
import com.netty.heima.b10_rpc.common.message.MessageCodecShareable;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelManager.class);

    private static Channel channel;

    public static Channel getChannel() {
        if( channel == null || !channel.isActive()){
            synchronized (ChannelManager.class) {
                if( channel == null || !channel.isActive()) {
                    channel = createChannel();
                }
            }
        }

        return channel;
    }

    private static Channel createChannel() {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        MessageCodecShareable messageCodecShareable = new MessageCodecShareable();
        RPCClientInboundHandler rpcClientInboundHandler = new RPCClientInboundHandler();

        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(clientGroup)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                                1024, 8, 4, 0, 0));

                        ch.pipeline().addLast(messageCodecShareable);

                        ch.pipeline().addLast(rpcClientInboundHandler);


//                        ch.pipeline().addLast( new ChannelInboundHandlerAdapter(){
//                            @Override
//                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                RPCRequestMessage rpcRequestMessage = new RPCRequestMessage(
//                                        "com.netty.heima.b10_rpc.common.service.HelloService",
//                                        "sayHello",
//                                        new Class[]{String.class},
//                                        new Object[]{"zhangsan"});
//                                ctx.writeAndFlush(rpcRequestMessage);
//                            }
//                        });
                    }
                });

        Channel channel = null;
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            LOGGER.debug("客户端连接已创建");
            channel = channelFuture.channel();

        } catch (InterruptedException e) {
            LOGGER.error("客户端错误", e.getMessage());
            throw new RuntimeException(e);
        }

        channel.closeFuture().addListener(future -> {
            clientGroup.shutdownGracefully();
        });

        return channel;
    }

}
