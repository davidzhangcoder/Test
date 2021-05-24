package interview.test;

class Test3Data {
    volatile boolean sub = true;

    //子线程循环2次，主线程循环2次，然后子线程循环2次，主线程循环2次，这样循环10次

    public synchronized void displayMain() throws InterruptedException {
        //判断
        while(sub) {
            wait();
        }

        //干活
        for (int i = 0; i < 2 ; i++) {
            System.out.println("Main run " + i );
        }

        //通知
        sub = true;
        notifyAll();
    }

    public synchronized void displaySub() throws InterruptedException {
        //判断
        while(!sub) {
            wait();
        }

        //干活
        for (int i = 0; i < 2 ; i++) {
            System.out.println("Sub run " + i );
        }

        //通知
        sub = false;
        notifyAll();
    }
}

public class Test3 {

    public static void main(String[] args) {
        Test3Data test3Data = new Test3Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    test3Data.displaySub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Sub").start();

        for (int i = 0; i < 10; i++) {
            try {
                test3Data.displayMain();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
