package interview.test;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test1Data {
    volatile int num = 1;
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    //编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
    // 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推

    public void printID() throws InterruptedException {
        lock.lock();
        try {
            String threadName = Thread.currentThread().getName();

            //判断
            while ((threadName.equalsIgnoreCase("A") && num != 1)
                    || (threadName.equalsIgnoreCase("B") && num != 2)
                    || (threadName.equalsIgnoreCase("C") && num != 3)
            ) {
                if (threadName.equalsIgnoreCase("A") && num != 1)
                    c1.await();
                else if (threadName.equalsIgnoreCase("B") && num != 2)
                    c2.await();
                else if (threadName.equalsIgnoreCase("C") && num != 3)
                    c3.await();
            }

            //工作
            System.out.println(threadName);

            //通知
            if (threadName.equalsIgnoreCase("A")) {
                num = 2;
                c2.signal();
            } else if (threadName.equalsIgnoreCase("B")) {
                num = 3;
                c3.signal();
            } else if (threadName.equalsIgnoreCase("C")) {
                num = 1;
                c1.signal();
            }
        }
        finally {
            lock.unlock();
        }
    }
}

public class Test1 {

    public static void main(String[] args) {
        Test1Data test1Data = new Test1Data();

        for (String s : Arrays.asList("A", "B", "C")) {
            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    try {
                        test1Data.printID();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } , s).start();
        }

    }

}
