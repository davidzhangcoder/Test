package a1.a_20210428_LinkedTransferQueue;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class Factory<T> {

    private Callable<T> producer;

    private Callable<T> consumer;

    private int pCount;

    private int cCount;

    private int produceThredhold;

    private ExecutorService executorService;

    private LinkedTransferQueue<T> linkedTransferQueue = new LinkedTransferQueue<T>();

    public Factory(Callable<T> producer, Callable<T> consumer, int pCount, int cCount, int produceThredhold) {
        this.producer = producer;
        this.consumer = consumer;
        this.pCount = pCount;
        this.cCount = cCount;
        this.produceThredhold = produceThredhold;

        this.executorService = Executors.newFixedThreadPool(pCount + cCount);
    }

    public void start() {
        for (int i = 0; i < pCount; i++) {
            Producer producer = new Producer();
            executorService.submit(producer);
        }

        for (int i = 0; i < cCount; i++) {
            Consumer consumer = new Consumer();
            executorService.submit(consumer);
        }
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            while( true ) {
                try {
                    T call = producer.call();
                    if (linkedTransferQueue.size() < produceThredhold) {
                        System.out.println(Thread.currentThread().getName() + " : " + linkedTransferQueue.size() + " : put :" + call);
                        linkedTransferQueue.put(call);
                    } else {
                        System.out.println(Thread.currentThread().getName() + " : " + linkedTransferQueue.size() + " : transfer :" + call);
                        //防止producer生产过快，如大于或等于produceThredhold,会调用transfer(),transfer()会阻塞当前线程，直到它所生产的被消费
                        linkedTransferQueue.transfer(call);
                        //linkedTransferQueue.put(call);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    };

    class Consumer implements Runnable {

        @Override
        public void run() {
            while( true ) {
                try {
                    T take = linkedTransferQueue.take();
                    System.out.println(Thread.currentThread().getName() + " : " + linkedTransferQueue.size() + " : consumer :" + take);
                    LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
