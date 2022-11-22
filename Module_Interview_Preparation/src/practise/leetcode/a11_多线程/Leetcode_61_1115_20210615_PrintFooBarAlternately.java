package practise.leetcode.a11_多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Leetcode_61_1115_20210615_PrintFooBarAlternately {

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        new Thread(()->{
            try {
                fooBar.foo(()->System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fooBar.bar(()->System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

class FooBar {
    private int n;

    private ReentrantLock lock = new ReentrantLock();
    volatile boolean flag = false;
    Condition f = lock.newCondition();
    Condition b = lock.newCondition();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while( flag ) {
                    f.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = true;
                b.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while( !flag ) {
                    b.await();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = false;
                f.signal();
            }finally {
                lock.unlock();
            }
        }
    }
}
