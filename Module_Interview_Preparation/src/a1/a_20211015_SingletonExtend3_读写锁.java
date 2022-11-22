package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class a_20211015_SingletonExtend3_读写锁 {

    //装载SingletonExtend实例的容器
    private static final Map<String, a_20211015_SingletonExtend3_读写锁> container = new HashMap<String, a_20211015_SingletonExtend3_读写锁>();

    //SingletonExtend类最多拥有的实例数量
    private static final int MAX_NUM = 3;

    //实例容器中元素的key的开始值
    private static String CACHE_KEY_PRE = "cache";

    private static AtomicInteger initNumber = new AtomicInteger(0);

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock r = lock.readLock();
    static Lock w = lock.writeLock();

    private a_20211015_SingletonExtend3_读写锁() {
        System.out.println("创建a_20211015_SingletonExtend3_读写锁实例1次！");
    }

    public static a_20211015_SingletonExtend3_读写锁 getInstance() {
        r.lock();
        try {
            a_20211015_SingletonExtend3_读写锁 instance = null;
            if ((instance = container.get(CACHE_KEY_PRE + initNumber)) == null) {
                //must release read lock before accquiring write lock
                r.unlock();
                try {
                    w.lock();
                    if ((instance = container.get(CACHE_KEY_PRE + initNumber)) == null) {
                        instance = new a_20211015_SingletonExtend3_读写锁();
                        container.put(CACHE_KEY_PRE + initNumber, instance);
                    }
                    r.lock();//锁降级－从写锁到读锁, downgrade by accquiring read lock before releasing write lock
                } finally {
                    w.unlock();
                }
            }

            initNumber.incrementAndGet();
            //控制容器中实例的数量
            if (initNumber.get() > 3) {
                initNumber.set(1);
            }

            return instance;
        }
        finally {
            r.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 1000 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    a_20211015_SingletonExtend3_读写锁 instance = a_20211015_SingletonExtend3_读写锁.getInstance();
                    if (instance == null) {
                        count.incrementAndGet();
                    }
                }
                countDownLatch.countDown();
            }, i + "").start();
        }

        countDownLatch.await();
        System.out.println("done");
        System.out.println(container.size());
        System.out.println(container.keySet());
        System.out.println(" null count is " + count.get());
        System.out.println(System.currentTimeMillis() - start);
    }

}

//https://blog.csdn.net/heqiangflytosky/article/details/49815603
//锁降级：写线程获取写入锁后可以获取读取锁，然后释放写入锁，这样就从写入锁变成了读取锁，从而实现锁降级的特性。
//锁升级：读取锁是不能直接升级为写入锁的。因为获取一个写入锁需要释放所有读取锁，所以如果有两个读取锁试图获取写入锁而都不释放读取锁时就会发生死锁。
