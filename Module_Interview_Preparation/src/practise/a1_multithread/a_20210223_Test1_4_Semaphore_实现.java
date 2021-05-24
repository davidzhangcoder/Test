package practise.a1_multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class a_20210223_Test1_4_Semaphore_实现 {

    //编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
    // 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推

    class TestAQS extends AbstractQueuedSynchronizer {

    }


    public static void main(String[] args) throws InterruptedException {

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                s2.release();
            }
        } , "t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                s3.release();
            }
        } , "t2");

        Thread t3 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    s3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                s1.release();
            }
        } , "t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
