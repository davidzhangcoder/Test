package com.netty.netty_pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty自带连接池的用法
 * 
 * @author Administrator
 *
 */
public class MyNettyPool {

    private static final Logger log = LoggerFactory.getLogger(MyNettyPool.class);

    // key为目标host，value为目标host的连接池
    public static ChannelPoolMap<String, FixedChannelPool> poolMap;
    private static final Bootstrap bootstrap = new Bootstrap();

    static {
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress("127.0.0.1", 8080);
    }

    public MyNettyPool() {
        init();
    }

    /**
     * netty连接池使用
     * 
     */
    public void init() {
        poolMap = new AbstractChannelPoolMap<String, FixedChannelPool>() {

            @Override
            protected FixedChannelPool newPool(String key) {
                ChannelPoolHandler handler = new ChannelPoolHandler() {
                    /**
                     * 使用完channel需要释放才能放入连接池
                     * 
                     */
                    @Override
                    public void channelReleased(Channel ch) throws Exception {
                        log.debug("channelReleased...... : {}", ch.id());
                        // 刷新管道里的数据
                        // ch.writeAndFlush(Unpooled.EMPTY_BUFFER); // flush掉所有写回的数据  
                        //System.out.println("channelReleased......");
                    }

                    /**
                     * 当链接创建的时候添加channelhandler，只有当channel不足时会创建，但不会超过限制的最大channel数
                     * 
                     */
                    @Override
                    public void channelCreated(Channel ch) throws Exception {
                        log.debug("Channel 已创建 : {}" , ch.id());

//                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

//                        ch.pipeline().addLast(new StringEncoder());
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
//                        ch.pipeline().addLast(new StringDecoder());
                         // 绑定channel的handler
                        //ch.pipeline().addLast(new NettyClientHandler());
                    }

                    /**
                     *  获取连接池中的channel
                     *  
                     */
                    @Override
                    public void channelAcquired(Channel ch) throws Exception {
                        log.debug("channelAcquired...... : {} " , ch.id());
                    }
                };
                
                return new FixedChannelPool(bootstrap, handler, 5); //单个host连接池大小
            }
        };

    }

    /**
     * 发送请求
     * 
     * @param msg
     *            请求参数
     * @return
     */
    public void send(final String msg) {

        
        //从连接池中获取连接
        final FixedChannelPool pool = poolMap.get("127.0.0.1");
        //申请连接，没有申请到或者网络断开，返回null
        Future<Channel> future = pool.acquire();
        future.addListener(new FutureListener<Channel>() {
            @Override
            public void operationComplete(Future<Channel> future) throws Exception {
                //给服务端发送数据
                Channel channel = future.getNow();

                int length = msg.length();
                byte[] bytes = msg.getBytes();
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeInt(length); //int型是4个字节
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);

//                channel.write(JSONObject.toJSONString(request));
//                channel.writeAndFlush(Constants.DELIMITER);

                log.debug("发送数据 - channel id : {}" , channel.id());

                // 连接放回连接池，这里一定记得放回去
                pool.release(channel);
            }
        });
        
        //获取服务端返回的数据
//        DefaultFuture defaultFuture = new DefaultFuture(request);
//        return defaultFuture.get();

    }

}