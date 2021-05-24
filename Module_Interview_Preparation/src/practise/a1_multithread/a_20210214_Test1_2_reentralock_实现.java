package practise.a1_multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class a_20210214_Test1_2_reentralock_实现 {

    //编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
    // 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推

    static class Test1Data {
        private volatile int num = 1;

        private ReentrantLock lock = new ReentrantLock(true);
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        private void printA() throws InterruptedException {
            try {
                lock.lock();

                while ( num != 1 ) {
                    System.out.println("A is waiting");
                    c1.await();
                }
                System.out.println("A");
                num = 2;
                c2.signal();
            }
            finally {
                lock.unlock();
            }
        }

        private void printB() throws InterruptedException {
            try {
                lock.lock();
                while( num != 2) {
                    System.out.println("B is waiting");
                    c2.await();
                }
                System.out.println("B");
                num = 3;
                c3.signal();
            }
            finally {
                lock.unlock();
            }
        }

        private void printC() throws InterruptedException {
            try {
                lock.lock();
                while( num != 3) {
                    System.out.println("C is waiting");
                    c3.await();
                }
                System.out.println("C");
                num = 1;
                c1.signal();
            }
            finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        Test1Data test1Data = new Test1Data();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    test1Data.printA();

                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++)
                    test1Data.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++)
                    test1Data.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();

    }
}
