package com.im.server;

import com.google.common.util.concurrent.ListenableFutureTask;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class IMServerExecutors {
    private static final Logger LOGGER = LoggerFactory.getLogger(IMServerExecutors.class);

    private static final Map<EventLoop, ScheduledExecutorService> EVENT_LOOP_THREAD_POOL_EXECUTOR_MAP
            = new ConcurrentHashMap<EventLoop, ScheduledExecutorService>();

    private static IMServerExecutors instance;

    private IMServerExecutors(){

    }

    public static IMServerExecutors getInstance(){
        if(instance == null ){
            synchronized (IMServerExecutors.class){
                if(instance==null){
                    instance = new IMServerExecutors();
                }
            }
        }

        return instance;
    }

    public void init(NioEventLoopGroup workerGroup) {
        if(EVENT_LOOP_THREAD_POOL_EXECUTOR_MAP.isEmpty()) {
            int count = workerGroup.executorCount();
            for (int i = 0; i < count; i++) {
                EventLoop next = workerGroup.next();
                EVENT_LOOP_THREAD_POOL_EXECUTOR_MAP.put(next, createExecutor());
            }
        }
    }

    public ScheduledExecutorService getExecutor(EventLoop eventLoop){
        return EVENT_LOOP_THREAD_POOL_EXECUTOR_MAP.get(eventLoop);
    }

    private ScheduledExecutorService createExecutor(){
        return Executors.newScheduledThreadPool(10);
        //return new ThreadPoolExecutor(5,5,0, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>());
    }

    public static void main(String[] args) {
        IMServerExecutors instance = IMServerExecutors.getInstance();

        ListenableFutureTask future = ListenableFutureTask.create(()->{
            LOGGER.debug("LockSupport.parkNanos");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
        },null);

        future.addListener(()->{
            LOGGER.debug("hello");
        }, instance.createExecutor());

        instance.createExecutor().execute(future);
    }
}
