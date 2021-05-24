package com.netty.heima.b3_TestEventLoopServerClient_version3.template;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b_20210509_TestEventLoopClient {

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
