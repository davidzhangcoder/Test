package practise.a1_multithread;

import java.util.concurrent.locks.LockSupport;

public class a_20210215_Test1_3_LockSupport_实现 {

    //编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
    // 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推

    static class Test1Data {

        private volatile int num = 1;

        private Thread t1;
        private Thread t2;
        private Thread t3;

        public Test1Data() {
        }

        public Test1Data(Thread t1, Thread t2, Thread t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }

        public void setT1(Thread t1) {
            this.t1 = t1;
        }

        public void setT2(Thread t2) {
            this.t2 = t2;
        }

        public void setT3(Thread t3) {
            this.t3 = t3;
        }

        private void printA() {
            LockSupport.park();
            System.out.println("A");
            LockSupport.unpark(t2);
        }

        private void printB() {
            LockSupport.park();
            System.out.println("B");
            LockSupport.unpark(t3);
        }

        private void printC() {
            LockSupport.park();
            System.out.println("C");
            LockSupport.unpark(t1);
        }

    }

    public static void main(String[] args) {
        Test1Data test1Data = new Test1Data();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                test1Data.printA();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                test1Data.printB();
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                test1Data.printC();
        });

        test1Data.setT1(t1);
        test1Data.setT2(t2);
        test1Data.setT3(t3);

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }

}
