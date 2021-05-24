package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//有问题
public class a_20210220_SingletonExtend3_读写锁 {

    static class SingtonExtend {

        static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //装载SingletonExtend实例的容器
        private static final Map<String, SingtonExtend> container = new HashMap<String, SingtonExtend>();

        //SingletonExtend类最多拥有的实例数量
        private static final int MAX_NUM = 3;

        //实例容器中元素的key的开始值
        private static String CACHE_KEY_PRE = "cache";

        private static volatile int initNumber = 1;

//        private static AtomicReference<Boolean> lock = new AtomicReference<>(false);

        private SingtonExtend() {
            System.out.println("SingtonExtend");
        }

        public static SingtonExtend getInstance(){
            readLock.lock();
            SingtonExtend singtonExtend = null;
            try {
                if((singtonExtend = container.get(CACHE_KEY_PRE+initNumber))!=null) {
                    return singtonExtend;
                }
            }
            finally {
                readLock.unlock();
                increaseInitNumber();
//                System.out.println("after increaseInitNumber");
            }

            synchronized ( SingtonExtend.class ) {
                if((singtonExtend = container.get(initNumber))==null) {
                    singtonExtend = new SingtonExtend();
                    container.put(CACHE_KEY_PRE + initNumber, singtonExtend);
                    increaseInitNumber();
                }
            }

            return singtonExtend;
        }

        private static void increaseInitNumber() {
            writeLock.lock();
            try{
                initNumber++;
                if( initNumber > 3 )
                    initNumber = 1;
            }
            finally {
                writeLock.unlock();
            }
        }

        public static void main(String[] args) {


            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 1000; j++) {
                        SingtonExtend instance = SingtonExtend.getInstance();
                    }
                }, i + "").start();
            }

            System.out.println(container.size());
            System.out.println(container.keySet());
            System.out.println(System.currentTimeMillis() - start);
        }

//        private void read() throws InterruptedException {
//            System.out.println("read");
//            readLock.lock();
//            try{
//                System.out.println("read lock");
//                TimeUnit.MILLISECONDS.sleep(1000);
//                System.out.println("read lock-1");
//            }
//            finally {
//                System.out.println("read unlock");
//                readLock.unlock();
//            }
//        }
//
//        private void write() throws InterruptedException {
//            System.out.println("write");
//            writeLock.lock();
//            try{
//                System.out.println("write lock");
//                TimeUnit.MILLISECONDS.sleep(2000);
//                System.out.println("write lock-1");
//            }
//            finally{
//                writeLock.unlock();
//            }
//        }
//
//        public static void main(String[] args) throws InterruptedException {
//            SingtonExtend singtonExtend = new SingtonExtend();
//
//            new Thread(()->{
//                try {
//                    singtonExtend.read();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"1").start();
//
//            TimeUnit.MILLISECONDS.sleep(100);
//
//            new Thread(()->{
//                try {
//                    singtonExtend.write();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"2").start();
//
//            TimeUnit.MILLISECONDS.sleep(100);
//
//            new Thread(()->{
//                try {
//                    singtonExtend.read();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },"1").start();
//
//            System.out.println("main thread");
//        }


    }
}
