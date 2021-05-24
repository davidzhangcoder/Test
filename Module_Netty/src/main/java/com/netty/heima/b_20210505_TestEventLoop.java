package com.netty.heima;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class b_20210505_TestEventLoop {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        EventLoop eventLoop = eventLoopGroup.next();

        eventLoop.submit(()->{
            System.out.println("a");
        });
    }
}
