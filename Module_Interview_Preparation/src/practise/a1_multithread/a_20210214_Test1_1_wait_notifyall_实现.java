package practise.a1_multithread;

public class a_20210214_Test1_1_wait_notifyall_实现 {

    //编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
    // 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推

    //用Object的wait和notify/notifyAll实现

//    http://www.zzcblogs.top/2018/03/15/java-notify%E5%92%8CnotifyAll%E5%8C%BA%E5%88%AB/
//    notify和notifyAll的区别
//    如果线程调用了对象的 wait()方法，那么线程便会处于该对象的等待池中，等待池中的线程不会去竞争该对象的锁。
//    当有线程调用了对象的 notifyAll()方法（唤醒所有 wait 线程）或 notify()方法（只随机唤醒一个 wait 线程），被唤醒的的线程便会进入该对象的锁池中，锁池中的线程会去竞争该对象锁。也就是说，调用了notify后只要一个线程会由等待池进入锁池，而notifyAll会将该对象等待池内的所有线程移动到锁池中，等待锁竞争
//    优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，唯有线程再次调用 wait()方法，它才会重新回到等待池中。而竞争到对象锁的线程则继续往下执行，直到执行完了 synchronized 代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。
//    Reference：线程间协作：wait、notify、notifyAll
//    综上，所谓唤醒线程，另一种解释可以说是将线程由等待池移动到锁池，notifyAll调用后，会将全部线程由等待池移到锁池，然后参与锁的竞争，竞争成功则继续执行，如果不成功则留在锁池等待锁被释放后再次参与竞争。而notify只会唤醒一个线程。

//    https://www.codenong.com/cs106535503/
//    等待池中的线程只有放到锁池中，才可以抢锁。比如notify唤醒线程，唤醒的线程会放到锁池中，与锁池中的线程一同抢锁

    static class Test1Data {
        private volatile int num = 1;

        private synchronized void printA() throws InterruptedException {
//            System.out.println(Thread.currentThread().getName() + " num=" + num);

            while (num != 1) {
//                this.notifyAll();//wrong
                this.wait();
            }
//            long sleeptime = (long) (Math.random() * 10);
//            System.out.println("A:"+sleeptime);
//            TimeUnit.SECONDS.sleep(sleeptime);

            System.out.println("A");
            num = 2;
//            this.notifyAll();
        }

        private synchronized void printB() throws InterruptedException {
//            System.out.println(Thread.currentThread().getName() + " num=" + num);

            while (num != 2) {
//                this.notifyAll();//wrong
                this.wait();
            }
//            long sleeptime = (long) (Math.random() * 10);
//            System.out.println("B:"+sleeptime);
//            TimeUnit.SECONDS.sleep(sleeptime);

            System.out.println("B");
            num = 3;
//            this.notifyAll();
        }

        private synchronized void printC() throws InterruptedException {
//            System.out.println(Thread.currentThread().getName() + " num=" + num);

            while (num != 3) {
//                this.notifyAll();//wrong
                this.wait();
            }
//            long sleeptime = (long) (Math.random() * 10);
//            System.out.println("C:"+sleeptime);
//            TimeUnit.SECONDS.sleep(sleeptime);

            System.out.println("C");
            num = 1;
//            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        Test1Data test1Data = new Test1Data();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++)
                    test1Data.printA();
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

    //创建3个对象是错误的
//    static class Test1Data {
//        private Test2Data t2;
//        private Test3Data t3;
//
//        public Test2Data getT2() {
//            return t2;
//        }
//
//        public void setT2(Test2Data t2) {
//            this.t2 = t2;
//        }
//
//        public Test3Data getT3() {
//            return t3;
//        }
//
//        public void setT3(Test3Data t3) {
//            this.t3 = t3;
//        }
//
//        private synchronized void printA() {
//            System.out.print("A");
//            try {
//                this.wait();
//                t2.notify();
////                t3.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    static class Test2Data {
//        private Test1Data t1;
//        private Test3Data t3;
//
//        public Test1Data getT1() {
//            return t1;
//        }
//
//        public void setT1(Test1Data t1) {
//            this.t1 = t1;
//        }
//
//        public Test3Data getT3() {
//            return t3;
//        }
//
//        public void setT3(Test3Data t3) {
//            this.t3 = t3;
//        }
//
//        private synchronized void printB() {
//            System.out.print("B");
//            try {
//                this.wait();
//                t3.notify();
////                t1.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    static class Test3Data {
//        private Test1Data t1;
//        private Test2Data t2;
//
//        public Test1Data getT1() {
//            return t1;
//        }
//
//        public void setT1(Test1Data t1) {
//            this.t1 = t1;
//        }
//
//        public Test2Data getT2() {
//            return t2;
//        }
//
//        public void setT2(Test2Data t2) {
//            this.t2 = t2;
//        }
//
//        private synchronized void printC() {
//            System.out.print("C");
//            try {
//                this.wait();
//                t1.notify();
////                t2.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public static void main(String[] args) throws InterruptedException {
//        Test1Data t1 = new Test1Data();
//        Test2Data t2 = new Test2Data();
//        Test3Data t3 = new Test3Data();
//
//        t1.setT2(t2);
//        t1.setT3(t3);
//
//        t2.setT1(t1);
//        t2.setT3(t3);
//
//        t3.setT1(t1);
//        t3.setT2(t2);
//
//        synchronized (t2) {
//            t2.wait();
//        }
//
//        synchronized (t3) {
//            t3.wait();
//        }
//
//        new Thread(()-> {
//            for (int i = 0; i < 10; i++) {
//                t1.printA();
//            }
//        },"A").start();
//        new Thread(()-> {
//            for (int i = 0; i < 10; i++) {
//                t2.printB();
//            }
//        },"B").start();
//        new Thread(()-> {
//            for (int i = 0; i < 10; i++) {
//                t3.printC();
//            }
//        },"C").start();
//    }


//    public class TestData {
//
//    }
//
//    public static void main(String[] args) {
//        Test1 test1 = new Test1();
//        TestData a = test1.new TestData();
//    }
}
