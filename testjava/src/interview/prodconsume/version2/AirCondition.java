package interview.prodconsume.version2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AirCondition {

    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                //wait();
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " " + num);

            //notifyAll();
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                //wait();
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " " + num);

            //notifyAll();
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

}
