package com.im.server;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ClientSessionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientSessionManager.class);

    private static ClientSessionManager instance;

    private final Map<Long, Channel> IM_SERVER_CHANNEL_MAP = new ConcurrentHashMap<Long, Channel>();

    private final AtomicLong count = new AtomicLong();

    private ClientSessionManager() {
    }

    public static ClientSessionManager getInstance() {
        if( instance == null ){
            synchronized (ClientSessionManager.class){
                if( instance == null ){
                    instance = new ClientSessionManager();
                }
            }
        }

        return instance;
    }

    public void increaseCount(){
        count.incrementAndGet();
        LOGGER.info("增加用户数至{}", count.get());
    }

    public void decreaseCount() {
        count.decrementAndGet();
        LOGGER.info("减少用户数至{}", count.get());
    }

    public void addClientChannel(long userID , Channel channel) {
        IM_SERVER_CHANNEL_MAP.putIfAbsent(userID, channel);
        LOGGER.info("绑定 用户ID:{} 和 通道ID: {}", userID , channel.id());
    }

    public void removeClientChannel(long userID){
        IM_SERVER_CHANNEL_MAP.remove(userID);
        LOGGER.info("移除绑定 用户ID:{} 和 通道", userID );
    }

    public Channel getClientChannel(long userID) {
        return IM_SERVER_CHANNEL_MAP.get(userID);
    }

}
