package a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class a_20210215_有界队列 {

    static class BoundQueue<T> {

        private Object[] items;

        private volatile int count = 0;

        Lock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();

        public BoundQueue(int size) {
            items = new Object[size];
        }

        private void add(T t) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length)
                    full.await();

                items[count] = t;
                if (count == 0)
                    empty.signal();
                count++;
            } finally {
                lock.unlock();
            }
        }

        private T remove() throws InterruptedException {
            T e = null;
            lock.lock();
            try {
                while (count == 0)
                    empty.await();

                e = (T) items[count-1];
                if (count == items.length)
                    full.signal();
                items[count-1] = null;
                count--;
            } finally {
                lock.unlock();
            }
            return e;
        }

        @Override
        public String toString() {
            return Arrays.toString(items);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundQueue<Integer> boundQueue = new BoundQueue<Integer>(3);

        new Thread(()->{
            try {
                boundQueue.add(1);
                System.out.println("add" + boundQueue);

                boundQueue.add(2);
                System.out.println("add" + boundQueue);

                boundQueue.add(3);
                System.out.println("add" + boundQueue);

                boundQueue.add(4);
                System.out.println("add" + boundQueue);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);

        boundQueue.remove();
        System.out.println("remove" + boundQueue);

        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
    }
}
