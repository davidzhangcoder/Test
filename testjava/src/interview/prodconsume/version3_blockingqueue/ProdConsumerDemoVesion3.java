package interview.prodconsume.version3_blockingqueue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class ProdConsumerDemoVersion3Data {

    volatile boolean flag = true;

    AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<Integer> blockingQueue = null;

    public ProdConsumerDemoVersion3Data(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void produce() throws InterruptedException {
        while( flag )
        {
            boolean offerResult = blockingQueue.offer(atomicInteger.incrementAndGet() , 1L, TimeUnit.SECONDS);
            System.out.println("offer : " + atomicInteger.get() + " : " + offerResult + " : blockingQueue.size " + blockingQueue.size());
        }
    }

    public void consumer() throws InterruptedException {
        while( flag )
        {
            Integer pollResult = blockingQueue.poll(1L, TimeUnit.SECONDS);
            System.out.println(pollResult);

            TimeUnit.SECONDS.sleep(1);
        }
    }
}

public class ProdConsumerDemoVesion3 {
    public static void main(String[] args) throws InterruptedException {
        //ArrayBlockingQueue不会自动扩容
        //如生产快，消费慢，这个实现会丢数据
        ProdConsumerDemoVersion3Data prodConsumerDemoVersion3Data = new ProdConsumerDemoVersion3Data(new ArrayBlockingQueue<Integer>(3 ));
        new Thread(()->{
                try {
                    prodConsumerDemoVersion3Data.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"Producer").start();

        new Thread(()->{
                try {
                    prodConsumerDemoVersion3Data.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"Consumer").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("after 5 seconds");

        prodConsumerDemoVersion3Data.flag=false;

//        ThreadPoolExecutor executorService = new
//                ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES,
//                new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.DiscardPolicy());
    }
}
