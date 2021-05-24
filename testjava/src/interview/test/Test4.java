package interview.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test4Data {
    volatile int start = 1;
    volatile char letterStart = 'A';
    volatile boolean doNum = true;

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();

    //写两个线程，一个线程打印152，另一个线程打印AZ，打印顺序是12A34B...5152Z

    public void displayNumber() throws InterruptedException {
        lock.lock();
        try {
            while (!doNum) {
                c1.await();
            }

            for (int i = start; i < start + 2 && i <= 52; i++) {
                System.out.println(Thread.currentThread().getName() + " " +i);
            }

            start = start+2;
            doNum=false;
            c1.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    public void displayLetter() throws InterruptedException {
        lock.lock();
        try {
            while (doNum) {
                c1.await();
            }

            for (char i = letterStart; i < letterStart + 1 && i <= 'Z'; i++) {
                System.out.println(Thread.currentThread().getName() + " " +(char)i);
            }

            letterStart++;
            doNum=true;
            c1.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

}

public class Test4 {
    public static void main(String[] args) throws InterruptedException {

        Test4Data test4Data = new Test4Data();

        new Thread(()->{
            for (int i = 1; i <= 26 ; i++) {
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    test4Data.displayNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();


        new Thread(()->{
            for (int i = 1; i <= 26 ; i++) {
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    test4Data.displayLetter();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

//        new Thread(()->{
//            for (int i = 1; i <= 26 ; i++) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    test4Data.displayNumber();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"C").start();
//
//        new Thread(()->{
//            for (int i = 1; i <= 26 ; i++) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    test4Data.displayLetter();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"D").start();

    }
}
