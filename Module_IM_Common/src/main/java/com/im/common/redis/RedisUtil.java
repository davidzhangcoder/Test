package com.im.common.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static final String PREFIX = "IMSERVER-TO-USER:";

    private static JedisPool pool;

    private static RedisUtil instance;


    private RedisUtil(){

    }

    private static void init(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        pool = new JedisPool(config, "127.0.0.1", 6379, 2000);
    }

    public static RedisUtil getInstance(){
        if(instance== null){
            synchronized (RedisUtil.class){
                if(instance==null){
                    instance=new RedisUtil();
                    init();
                }
            }
        }

        return instance;
    }

    public void setUserOnline(String key, long userID, boolean online) {
        Jedis jedis=null;
        try {
            jedis = pool.getResource();
            jedis.setbit(PREFIX+key, userID, online);
        }
        finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public boolean isUserOnline(String key, long userID) {
        Jedis jedis=null;
        try {
            jedis = pool.getResource();
            return jedis.getbit(PREFIX+key, userID);
        }
        finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public void close(){
        if(pool != null)
            pool.close();
    }

    public static void main(String[] args) {

        //基本使用
//        Jedis jedis = new Jedis("localhost", 6379);  //指定Redis服务Host和port
//        String value = jedis.get("foo"); //访问Redis服务
//        System.out.println(value);
//        jedis.close(); //使用完关闭连接

        //连接池使用
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 2000);
        Jedis jedis = pool.getResource();
        String value = jedis.get("foo");
        System.out.println(value);
        jedis.close();
        pool.close();
    }
}
