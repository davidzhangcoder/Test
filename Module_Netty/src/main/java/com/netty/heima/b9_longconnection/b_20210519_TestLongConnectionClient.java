package com.netty.heima.b9_longconnection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class b_20210519_TestLongConnectionClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(b_20210519_TestLongConnectionClient.class);

    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(clientGroup)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(0,5,0));

                            ch.pipeline().addLast(new ClientIdleUserEventHandler());

                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                    new Thread(()->{
//                                        Scanner scan = new Scanner(System.in);
//                                        while(true) {
//                                            System.out.println(count.getAndIncrement() + "请输入：");
//                                            String input = scan.nextLine();
//                                            ByteBuf buffer = ctx.alloc().buffer();
//                                            buffer.writeBytes(input.getBytes());
//                                            ctx.writeAndFlush(buffer);
//                                        }
//                                    },"Client Input").start();

                                    System.out.println(count.getAndIncrement() + "请输入：");

                                }
                            });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();

//            for (int i = 0; i <60000 ; i++) {
//                bootstrap.connect("127.0.0.1", 8080).sync();
//            }

            LOGGER.debug("客户端连接已创建");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("客户端错误", e.getMessage());
        } finally {
            clientGroup.shutdownGracefully();
        }
    }

}
