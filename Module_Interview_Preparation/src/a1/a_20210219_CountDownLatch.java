package a1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class a_20210219_CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    long waitingPeriod = (long) (Math.random() * 10);
                    System.out.println("waitingPeriod: "+ waitingPeriod);
                    TimeUnit.SECONDS.sleep(waitingPeriod);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            },i+"").start();
        }

        countDownLatch.await();

        System.out.println("countDownLatch is done");

        LongAdder longAdder;

        Random random = new Random();
        random.nextInt();
    }

}
