package interview.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class Test8Data {

    // 实现一个容器，提供两个方法，add，size
    // 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束

    List<Integer> list = new ArrayList<Integer>();

    public void add(Integer value) {
        list.add(value);
    }

    public int size() {
        return list.size();
    }
}

public class Test8 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Test8Data test8Data = new Test8Data();

        Thread thread1 = new Thread(() -> {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 10; i++) {
                test8Data.add(i);
                countDownLatch.countDown();
                if (test8Data.size() == 5)
                    break;
            }

        }, "1");

        Thread thread2 = new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + " : finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Main finished : " + test8Data.size());
    }
}
