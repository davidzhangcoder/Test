package com.im.client;

import com.im.common.cluster.IMServerCluster;
import com.im.common.configuration.IMServerNode;
import com.im.common.message.ChatRequestMessage;
import com.im.common.message.MessageCodecShareable;
import com.im.handler.ClientInitialClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class IMClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMClient.class);

    public static void main(String[] args) {
        IMClient imClient = new IMClient();
        IMServerCluster.getInstance().init();

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        Channel channel = imClient.getChannel(imClient.getServer());

        new Thread(()->{
            while(true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("from用户ID:");
                String fromUserID = scanner.nextLine();
                System.out.println("from用户名:");
                String fromUserName = scanner.nextLine();
                System.out.println("message:");
                String message = scanner.nextLine();

                System.out.println("to用户ID:");
                String toUserID = scanner.nextLine();
                System.out.println("to用户名:");
                String toUserName = scanner.nextLine();

                ChatRequestMessage chatRequestMessage = new ChatRequestMessage(
                        Integer.parseInt(fromUserID),
                        fromUserName,
                        Integer.parseInt(toUserID),
                        toUserName,
                        message
                );
                channel.writeAndFlush(chatRequestMessage);
            }
        }).start();
    }

    private IMServerNode getServer(){
//        IMServerNode availableIMServer = IMServerCluster.getInstance().getAvailableLoadBalencedIMServer();
        IMServerNode availableIMServer = IMServerCluster.getInstance().getAllIMServers().get(1);
        return availableIMServer;
    }

    public Channel getChannel(IMServerNode availableIMServer) {

        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        final MessageCodecShareable messageCodecShareable = new MessageCodecShareable();
        final ClientInitialClientHandler clientInitialClientHandler = new ClientInitialClientHandler();

        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(clientGroup)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                                1024, 8, 4, 0, 0));

                        ch.pipeline().addLast(messageCodecShareable);

                        ch.pipeline().addLast(ClientInitialClientHandler.NAME, clientInitialClientHandler);
                    }
                });

        Channel channel = null;
        try {
            ChannelFuture channelFuture = bootstrap.connect(availableIMServer.getHost(), availableIMServer.getPort()).sync();
            LOGGER.info("客户端连接已创建, 已连接到服务器: host={}, port={}", availableIMServer.getHost(), availableIMServer.getPort());
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
