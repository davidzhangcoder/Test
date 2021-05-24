package com.netty.heima.b1_TestEventLoopServerClient_version1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class b_20210505_TestEventLoopClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210505_TestEventLoopClient.class);

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080);
        Channel channel = channelFuture.channel();

        //方法一：同步执行
        channelFuture.sync();
        InetSocketAddress socketAddress = (InetSocketAddress) channel.localAddress();
        channel.writeAndFlush(socketAddress.getHostString()+ ":" + socketAddress.getPort() + " connected ");


        //方法二：异步执行
//        channelFuture.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                LOGGER.debug("客户端连接已创建");
//                Channel channel = channelFuture.channel();
//                InetSocketAddress socketAddress = (InetSocketAddress) channel.localAddress();
//                channel.writeAndFlush(socketAddress.getHostString()+ ":" + socketAddress.getPort() + " connected ");
//            }
//        });


        new Thread(()->{
            Scanner scan = new Scanner(System.in);
            while( true ) {
                // 从键盘接收数据
                // nextLine方式接收字符串
//            System.out.println("nextLine方式接收：");
                // 判断是否还有输入
                if (scan.hasNextLine()) {
                    String str2 = scan.nextLine();
                    System.out.println("输入的数据为：" + str2);
                    if(!"q".equalsIgnoreCase(str2))
                        channel.writeAndFlush(str2);
                    else {
                        LOGGER.debug("准备关闭连接");
                        channel.close();
                        scan.close();
                        break;
                    }
                }
            }
        }, "Input-Thread").start();

        //channel.close()会触发channel.closeFuture()的listener
        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                LOGGER.debug("连接已关闭");
                Future<?> future1 = workerGroup.shutdownGracefully();
            }
        });

    }

}
