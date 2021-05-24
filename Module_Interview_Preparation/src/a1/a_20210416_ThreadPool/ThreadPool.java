package a1.a_20210416_ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ThreadPool implements I_ThreadPool{

    private BlockingQueue<Runnable> blockingQueue;

    private int coreSize;

    private int maxSize;

    private int keepAliveTime;

    private AtomicInteger count = new AtomicInteger(0);


    public ThreadPool(BlockingQueue blockingQueue, int coreSize, int maxSize, int keepAliveTime) {
        this.blockingQueue = blockingQueue;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
    }

    private void init() {
    }

    public int getQueueSize(){
        return blockingQueue.size();
    }

    @Override
    public void submit(Runnable runnable) {
//        if( count.get() >= maxSize ) {
//            blockingQueue.offer(runnable);
//        } else {
//            //todo:
//            //这里有问题，会产生大于maxSize的Thread
//            Thread thread = new Thread(() -> {
//
//                Runnable task = runnable;
//                while( task != null || (task = getTask()) != null ) {
//                    task.run();
//                    task = null;
//                }
//
//            }, "Theader-" + count.incrementAndGet());
//            //thread.setDaemon(true);
//
//            thread.start();
//        }

        int i = count.incrementAndGet();
        if( i > maxSize) {
            blockingQueue.offer(runnable);
        } else {
            Thread thread = new Thread(() -> {

                Runnable task = runnable;
                while( task != null || (task = getTask()) != null ) {
                    task.run();
                    task = null;
                }

            }, "Theader-" + i);
            //thread.setDaemon(true);

            thread.start();
        }
    }

    private Runnable getTask() {
        Runnable take = null;
        try {
            boolean timed = count.get()>coreSize?true:false;
            take = timed?blockingQueue.poll(keepAliveTime, TimeUnit.SECONDS):blockingQueue.take();
        } catch (InterruptedException e) {
            System.out.println("error");
            return null;
        }
        return take;
    }

    public static void main(String[] args) {
//        ThreadPool threadPool = new ThreadPool( new ArrayBlockingQueue(10) , 3 , 3, 1);
//
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();
//        new Thread(()->{
//            test1(threadPool);
//        }).start();

        test2();
    }

    private static void test1(ThreadPool threadPool) {
        for (int i = 0; i < 30; i++) {
            Runnable a = new Runnable() {
                @Override
                public void run() {
                    try {
                        int i = ThreadLocalRandom.current().nextInt(3);
                        System.out.println(Thread.currentThread().getName() + " sleeps " + i + " seconds ");

                        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(i));
                    }catch (Throwable e) {
                        System.out.println(e);
                    }
                }
            };

            threadPool.submit( a );
            //System.out.println("queue size: " + threadPool.getQueueSize());
        }
    }

    private static void test2(){
        try{
            System.out.println("1");
            return;
        }
        finally {
            System.out.println("2");
        }
    }
}
