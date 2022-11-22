package practise.a1_multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class a_20211010_Test1_redo {

    static class Data {

        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        volatile boolean aRuning = true;
        volatile boolean bRuning = false;

        volatile int aCount = 0;
        volatile int bCount = 0;


        public void displayA(String v) throws InterruptedException {
            try {
                lock.lock();
                while (aRuning == false) {
                    a.await();
                }
                System.out.print("A" + v);

                aCount++;
                while( true ) {
                    if (aCount == 2) {
                        break;
                    }
                }

                aCount=0;
                b.signal();
                bRuning = true;
                aRuning = false;
            }
            finally {
                lock.unlock();
            }
        }

        public void displayB(String v) throws InterruptedException {
            try {
                lock.lock();
                while (bRuning == false) {
                    b.await();
                }
                System.out.print("B" + v);

                bCount++;
                while( true ) {
                    if (bCount == 2) {
                        break;
                    }
                }

                a.signal();
                aRuning = true;
                bRuning = false;
            }
            finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.displayA("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a1").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.displayA("2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a2").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.displayB("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "b1").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.displayB("2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "b2").start();

    }
}
