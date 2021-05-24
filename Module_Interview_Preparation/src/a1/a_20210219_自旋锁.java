package a1;

import java.util.concurrent.atomic.AtomicReference;

public class a_20210219_自旋锁 {
    static class SpinLock {
        private AtomicReference<Thread> ref = new AtomicReference<Thread>(null);

        private void lock() {
            Thread thread = Thread.currentThread();
            while(!ref.compareAndSet(null,thread)){
            }
        }

        private void unLock() {
            Thread thread = Thread.currentThread();
            if( ref.get() == thread ){
                ref.set(null);
            }
        }
    }

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();
        for (int j = 0; j < 10; j++) {

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    spinLock.lock();
                    count++;
                    spinLock.unLock();
                }
            }, "A");

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    spinLock.lock();
                    count++;
                    spinLock.unLock();
                }
            }, "B");

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        }

        System.out.println(count);
    }
}
