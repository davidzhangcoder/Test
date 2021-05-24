package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class a_20210220_SingletonExtend1 {

    //装载SingletonExtend实例的容器
    private static final Map<String, a_20210220_SingletonExtend1> container = new HashMap<String, a_20210220_SingletonExtend1>();
    //SingletonExtend类最多拥有的实例数量
    private static final int MAX_NUM = 3;
    //实例容器中元素的key的开始值
    private static String CACHE_KEY_PRE = "cache";
    private static AtomicInteger initNumber = new AtomicInteger(1);

    private a_20210220_SingletonExtend1() {
        System.out.println("创建SingletonExtend实例1次！");
    }

    //先从容器中获取实例，若实例不存在，在创建实例，然后将创建好的实例放置在容器中
    public synchronized static a_20210220_SingletonExtend1 getInstance() {
        String key = CACHE_KEY_PRE + initNumber;
        a_20210220_SingletonExtend1 singletonExtend = container.get(key);
        if (singletonExtend == null) {

            singletonExtend = container.get(key);
            if (singletonExtend == null) {
                singletonExtend = new a_20210220_SingletonExtend1();
                container.put(key, singletonExtend);
            }
            initNumber.incrementAndGet();
            //控制容器中实例的数量
            if (initNumber.get() > 3) {
                initNumber.set(1);
            }

        }
        return singletonExtend;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    a_20210220_SingletonExtend1 instance = a_20210220_SingletonExtend1.getInstance();
                }
            }, i + "").start();
        }

        System.out.println(container.size());
        System.out.println(container.keySet());

        System.out.println(System.currentTimeMillis() - start);


    }

}
