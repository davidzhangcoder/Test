package com.netty.heima;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

public class TestNettyPromise {

    private static final Logger log = LoggerFactory.getLogger(TestNettyPromise.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        EventLoop eventLoop = nioEventLoopGroup.next();
        DefaultPromise<Integer> defaultPromise = new DefaultPromise<Integer>(eventLoop);

        new Thread(()->{
            log.debug("sub thread start : ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            defaultPromise.setSuccess(10);
            log.debug("value setSucess ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            try{
//                int i = 1 /0;
//            }
//            catch(Exception e) {
//                log.debug("value setFailure ");
//                defaultPromise.setFailure(e);
//            }
            log.debug("sub thread done : ");
        },"sub-thread").start();

        defaultPromise.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug(future.get()+"");
                log.debug("operationComplete : {} , {} , {}" , future.isDone(), future.isSuccess(), future.isCancelled());
                if (future.isDone()&&future.isSuccess()) {
                    log.debug("done : " + future.get());
                }
            }
        });

        defaultPromise.get();
    }

}
