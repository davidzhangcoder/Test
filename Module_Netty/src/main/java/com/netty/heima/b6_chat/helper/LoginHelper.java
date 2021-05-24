package com.netty.heima.b6_chat.helper;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class LoginHelper {

    private static LoginHelper loginHelper;

    private ConcurrentHashMap<String, Channel> userNameToChannelMap = new ConcurrentHashMap<String, Channel>();

    public static LoginHelper getInstance(){
        if( loginHelper == null ){
            synchronized (LoginHelper.class){
                if( loginHelper == null )
                    loginHelper = new LoginHelper();
            }
        }

        return loginHelper;
    }

    private LoginHelper(){

    }

    public void bind(String username, Channel channel) {
        userNameToChannelMap.putIfAbsent(username, channel);
    }

    public Channel getChannel(String username) {
        return userNameToChannelMap.get(username);
    }
}
