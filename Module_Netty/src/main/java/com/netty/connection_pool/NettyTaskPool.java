package com.netty.connection_pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NettyTaskPool {

    private static final Logger log = LoggerFactory.getLogger(NettyTaskPool.class);

    /**
     * 线程池线程数量,对应CachedThreadPoolExecutor
     */
    private static final int CORE_POLL_SIZE = 0;
    private static final int MAX_POLL_SIZE = Integer.MAX_VALUE;
	 
    //手动创建线程池
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POLL_SIZE,
            MAX_POLL_SIZE,
            3,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static String submitTask(String message) throws Exception{
        //单个任务在线程池内分配单个线程，用于同步等待封装的返回结果
        Future<String> submit = threadPool.submit(new ChannelTaskThread(message));
        String response = submit.get();
        log.info("\n\t submitTask 返回的 Response：  \r\n\t\t[ "+ response +" ]\n");
        return response;
    }

}