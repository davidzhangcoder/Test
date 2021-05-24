package a1;

import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.LongAdder;

public class a_20210220 {


    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        HashMap a ;
        Unsafe b;

        BlockingQueue<Long> blockingQueue = new ArrayBlockingQueue<Long>(10);

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    longAdder.increment();
                    long sum = longAdder.sum();
                    try {
                        blockingQueue.put(sum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +" : "+sum + " " + blockingQueue.size());
                }
            }
        };

        for (int i = 0; i < 100 ; i++) {
            new Thread(producer, i+"" ).start();
        }
    }
}
