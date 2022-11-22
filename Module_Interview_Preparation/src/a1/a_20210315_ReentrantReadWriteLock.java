package a1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class a_20210315_ReentrantReadWriteLock {

    static class Data {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock r = lock.readLock();
        Lock w = lock.writeLock();

        private volatile int i = 0;

        public int read() {
            r.lock();
            try {
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
                return i;
            } finally {
                r.unlock();
            }
        }

        public int write() {
            w.lock();
            try {
                return ++i;
            } finally {
                w.unlock();
            }
        }
    }

    public static void main(String[] args) {
        //测试: 读锁拿到锁后，写锁是拿不到锁的，必须等读锁释放锁后 - 尽管有1000个线程，但读可以并行，所以大约等待所设置的5后，写锁会打印1
//        Data data = new Data();
//        for (int i = 0; i < 1000; i++) {
//            new Thread(()->{
//                data.read();
//            }).start();
//        }
//        new Thread(()->{
//            System.out.println(data.write());
//        }).start();

        //测试: 读锁拿到锁后，写锁是拿不到锁的，必须等读锁释放锁后 － 所以读锁会打印0
//        Data data = new Data();
//        new Thread(() -> {
//            System.out.println(data.read());
//        }).start();
//        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
//        new Thread(() -> {
//            data.write();
//        }).start();

        System.out.println("Main");


        Data data1 = new Data();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                data1.read();
            }).start();
        }
        new Thread(() -> {
            System.out.println(data1.write());
        }).start();

    }
}
