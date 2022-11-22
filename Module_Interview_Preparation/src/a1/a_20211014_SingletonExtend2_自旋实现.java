package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class a_20211014_SingletonExtend2_自旋实现 {

    static class SpinLock {
        AtomicReference<Thread> lock = new AtomicReference<Thread>();

        public void lock(Thread thread) {
            while (!lock.compareAndSet(null, thread)) {

            }
        }

        public void unlock(Thread thread) {
            lock.compareAndSet(thread,null);
        }
    }

    //装载SingletonExtend实例的容器
    private static final Map<String, a_20211014_SingletonExtend2_自旋实现> container = new HashMap<String, a_20211014_SingletonExtend2_自旋实现>();

    //SingletonExtend类最多拥有的实例数量
    private static final int MAX_NUM = 3;

    //实例容器中元素的key的开始值
    private static String CACHE_KEY_PRE = "cache";

    private static AtomicInteger initNumber = new AtomicInteger(1);

    static SpinLock spinLock = new SpinLock();


    private a_20211014_SingletonExtend2_自旋实现(){
        System.out.println("自旋实现");
    }

    public static a_20211014_SingletonExtend2_自旋实现 getInstance(){
        try {
            spinLock.lock(Thread.currentThread());

            a_20211014_SingletonExtend2_自旋实现 instance = null;
            if( (instance = container.get(CACHE_KEY_PRE+initNumber)) == null ){
                instance = new a_20211014_SingletonExtend2_自旋实现();
                container.put(CACHE_KEY_PRE+initNumber,instance);
            }
            initNumber.incrementAndGet();
            //控制容器中实例的数量
            if (initNumber.get() > 3) {
                initNumber.set(1);
            }

            //int i = 1/0;

            return instance;
        }
        finally {
            spinLock.unlock(Thread.currentThread());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    a_20211014_SingletonExtend2_自旋实现 instance = a_20211014_SingletonExtend2_自旋实现.getInstance();
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

// for loop 10000 1000
//    自旋实现
//            自旋实现
//自旋实现
//        3
//        [cache3, cache2, cache1]
//        null count is 0
//        64721

