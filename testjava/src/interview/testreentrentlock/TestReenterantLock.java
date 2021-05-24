package interview.testreentrentlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReenterantLock {

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();

        for (int i = 0; i < 10; i++) {
            //拿到method1的锁，肯定也会拿到method2的锁
            //同一个线程中的method1和method2肯定是一起执行（ 没有下面的join也是一样的 ）
            //下面的join,只是测试，是另一个练习
            Thread thread1 = new Thread(() ->{ data.method1(); }, i+"");
            thread1.start();

            //用join保证有序，等thread1执行完，主线程才会执行，即下一次的for loop
            thread1.join();
        }
    }

}

class Data {
    Lock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        try{
            System.out.println( Thread.currentThread().getName() + " : method1 ");
            method2();
        }
        finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try{
            System.out.println( Thread.currentThread().getName() + " : method2 ");
        }
        finally {
            lock.unlock();
        }
    }
}

//class Data1 {
//
//
//    public void method1(Lock lock) {
//        lock.lock();
//        try{
//            System.out.println( Thread.currentThread().getName() );
//        }
//        finally {
//            lock.unlock();
//        }
//    }
//}

