package interview.reentrantreadwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Data {
    private Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void set(String key, Object value) {
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 正在写入 ");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + " 写入完成 " + key);
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 正在读取 ");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成 " + key + " : " +result);
        }
        finally {
            readWriteLock.readLock().unlock();
        }

    }
}

public class ReadWriteLock {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                data.set(i+"",i);
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                data.get(i+"");
            }
        },"B").start();

    }

}
