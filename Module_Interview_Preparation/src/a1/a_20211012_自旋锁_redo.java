package a1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class a_20211012_自旋锁_redo {

    static class SpinLock {
        AtomicReference<Thread> lock = new AtomicReference<Thread>();

        public void lock(Thread thread) {
            while (!lock.compareAndSet(null, thread)) {

            }
        }

        public void unlock(Thread thread) {
            lock.compareAndSet(thread,null);
        }
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{

                try {
                    System.out.println(Thread.currentThread().getName() + " arrived barrier1 ");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " go ");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                spinLock.lock(Thread.currentThread());

                System.out.println(Thread.currentThread().getName());

                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                spinLock.unlock(Thread.currentThread());

                try {
                    System.out.println(Thread.currentThread().getName() + " arrived barrier2 ");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " end ");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, "t"+i).start();
        }
    }

}
