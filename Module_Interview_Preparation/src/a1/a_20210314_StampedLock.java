package a1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

public class a_20210314_StampedLock {

    static class Data {
        private volatile  int  i =0;

        private StampedLock lock = new StampedLock();

        private int read() {
            long stamp = lock.tryOptimisticRead();
            int result = i;
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
            while(!lock.validate(stamp)){
                stamp = lock.tryOptimisticRead();
                result = i;
            }
            return result;
        }

        private int readEsculated(){
            long stamp = lock.tryOptimisticRead();
            int result = i;
            if(!lock.validate(stamp)){
                long stampReadLock = lock.readLock();
                try{
                    result = i;
                }finally {
                    lock.unlockRead(stampReadLock);
                }
            }
            return result;
        }

        private void write(){
            long stamp = lock.writeLock();
            try{
                i++;
            }finally {
                lock.unlockWrite(stamp);
            }
        }
    }

    public static void main(String[] args) {
        ////测试: 读锁拿到乐观锁后，写锁是可以拿到锁的 － 所以读锁，再写锁更新数值后, 由于stamp会invalidate, 所以会重新取数值，并会打印1
        Data data = new Data();
        new Thread(()->{
            System.out.println(data.read());
        }).start();

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));

        new Thread(()->{
            data.write();
        }).start();

        System.out.println("Main");
    }

}