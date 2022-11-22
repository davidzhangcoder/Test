package com.im.server;

import com.im.common.cluster.IMServerCluster;
import com.im.common.configuration.IMServerNode;
import com.im.common.configuration.PropertiesLoader;
import com.im.common.message.MessageCodecShareable;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class IMServerClusterServerSide extends IMServerCluster {

    private final Map<IMServerNode, Channel> PEER_SERVER_MAP = new ConcurrentHashMap<>();

    protected static IMServerClusterServerSide instance = new IMServerClusterServerSide();

    private IMServerClusterServerSide(){
        super();
    }

    public static IMServerClusterServerSide getInstance(){
        return instance;
    }

    public Channel getPeerNodeChannel(IMServerNode imServerNode) {
        return PEER_SERVER_MAP.get(imServerNode);
    }

    @Override
    protected void doAfterChildNodeAdded(ChildData data) throws Exception {
        super.doAfterChildNodeAdded(data);

        String[] address = new String(data.getData()).split(":");
        String host = address[0];
        int port = Integer.parseInt(address[1]);

        IMServerNode currentIMServerNode = PropertiesLoader.getIMServerNode();
        if( !currentIMServerNode.getHost().equalsIgnoreCase(host) || currentIMServerNode.getPort() != port ) {
            Channel channel = PeerServerChannelManager.createChannel(host, port);
            PEER_SERVER_MAP.put(new IMServerNode(host, port), channel);
        }
    }

    @Override
    protected void doAfterChildNodeRemoved(ChildData data){
        super.doAfterChildNodeRemoved(data);

        String[] address = new String(data.getData()).split(":");
        String host = address[0];
        int port = Integer.parseInt(address[1]);

        PEER_SERVER_MAP.remove(new IMServerNode(host,port));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<?> submit = executorService.submit(() -> {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
            return 1;
        });

        System.out.println(submit.get());
    }

    static class PeerServerChannelManager {

        private static final Logger LOGGER = LoggerFactory.getLogger(PeerServerChannelManager.class);

        public static Channel createChannel(String host, int port) {
            NioEventLoopGroup clientGroup = new NioEventLoopGroup();

            MessageCodecShareable messageCodecShareable = new MessageCodecShareable();

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
                        }
                    });

            Channel channel = null;
            try {
                ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
                LOGGER.info("Peer IMServerNode: host={}, port={} 连接已创建", host, port);
                channel = channelFuture.channel();

            } catch (InterruptedException e) {
                LOGGER.error("Peer IMServerNode 错误", e.getMessage());
                throw new RuntimeException(e);
            }

            channel.closeFuture().addListener(future -> {
                clientGroup.shutdownGracefully();
            });

            return channel;
        }

    }


}
