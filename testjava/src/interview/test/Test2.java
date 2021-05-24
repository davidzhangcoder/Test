package interview.test;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test2Data {
    volatile int count = 0;
    volatile int num = 1;
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    // 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15.
    // 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75.

    public void printID() throws InterruptedException {
        while(count<75) {
            lock.lock();
            try {

                String threadName = Thread.currentThread().getName();

                //判断
                while (
                        (threadName.equalsIgnoreCase("A") && num != 1)
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

                if( count < 75 ) {
                    //工作
                    for (int i = 0; i < 5; i++) {
                        count++;
                        System.out.println(threadName + " " + count);
                    }
                }

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
            } finally {
                lock.unlock();
            }
        }
    }
}

public class Test2 {

    public static void main(String[] args) {
        Test2Data test2Data = new Test2Data();

        for (String s : Arrays.asList("A", "B", "C")) {
            new Thread(()->{
                try {
                    test2Data.printID();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } , s).start();
        }
    }
}
