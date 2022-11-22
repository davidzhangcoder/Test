package com.im.server;

import com.im.common.configuration.IMServerNode;
import com.im.common.configuration.PropertiesLoader;
import com.im.common.message.MessageCodecShareable;
import com.im.common.redis.RedisUtil;
import com.im.handler.ClientInitialServerHandler;
import com.im.handler.ClientMessageHandler;
import com.im.register.IRegister;
import com.im.register.Register;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IMServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMServer.class);
    
    public static void main(String[] args) {
        IMServer imServer = new IMServer();
        imServer.start();
    }

    private void start() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        IMServerNode imServerNode = PropertiesLoader.getIMServerNode();
        IRegister register = Register.getRegister();
        IMServerExecutors.getInstance().init(workerGroup);

        final MessageCodecShareable messageCodecShareable = new MessageCodecShareable();
        final ClientInitialServerHandler clientInitialServerHandler = new ClientInitialServerHandler();
        final ClientMessageHandler clientMessageHandler = new ClientMessageHandler();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .channel(NioServerSocketChannel.class)
                    .group(bossGroup, workerGroup)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.attr(ServerAttributeKey.CHANNEL_IM_SERVER_NODE).set(imServerNode);

                            //ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                                    1024, 8, 4, 0, 0));

                            ch.pipeline().addLast(messageCodecShareable);

                            ch.pipeline().addLast(clientInitialServerHandler);

                            ch.pipeline().addLast(clientMessageHandler);
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(imServerNode.getPort()).sync();
            LOGGER.debug("服务器已启动");

            register.register(imServerNode);

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("server error", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            register.close();
            RedisUtil.getInstance().close();
        }
    }

}
