package interview.testArrayList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyData {

    private int i = 0;
    Lock lock = new ReentrantLock();

    //synchronized锁整个类
//    public synchronized int getValue() {
//        return i;
//    }
//
//    public synchronized void setValue(int value) {
//        this.i = value;
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public int getValue() {
        lock.lock();
        try {

            return i;
        }
        finally {
            lock.unlock();
        }
    }

    //ReentrantLock锁代码块
    public void setValue(int value) {

        lock.lock();

        try {
            this.i = value;
        }
        finally {
            lock.unlock();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
