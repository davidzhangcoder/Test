package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class a_20210220_SingletonExtend4_ReentrentLock {

    static class SingtonExtend {

        static ReentrantLock lock = new ReentrantLock();

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

        public static SingtonExtend getInstance() {

            SingtonExtend singtonExtend = container.get(CACHE_KEY_PRE + initNumber);

            if (singtonExtend == null) {
                synchronized (SingtonExtend.class) {
                    if ( (singtonExtend = container.get(CACHE_KEY_PRE + initNumber)) == null) {
                        singtonExtend = new SingtonExtend();
                        container.put(CACHE_KEY_PRE + initNumber, singtonExtend);
                    }
                }
            }

            synchronized (SingtonExtend.class) {
                if (initNumber < 3)
                    initNumber++;
                else
                    initNumber = 1;
            }
            return singtonExtend;
        }

        public static void main(String[] args) {


            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
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


    }

}
