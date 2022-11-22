package a1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


//自旋实现:高并发下，浪费资源，不断forloop
public class a_20210220_SingletonExtend2_自旋实现 {

    //装载SingletonExtend实例的容器
    private static final Map<String, a_20210220_SingletonExtend2_自旋实现> container = new HashMap<String, a_20210220_SingletonExtend2_自旋实现>();

    //SingletonExtend类最多拥有的实例数量
    private static final int MAX_NUM = 3;

    //实例容器中元素的key的开始值
    private static String CACHE_KEY_PRE = "cache";

    private static volatile int initNumber = 1;

    private static AtomicReference<Boolean> lock = new AtomicReference<>(false);

    private a_20210220_SingletonExtend2_自旋实现() {
        System.out.println("a_20210220_SingletonExtend_自旋实现");
    }

    public static a_20210220_SingletonExtend2_自旋实现 getInstance() {
        a_20210220_SingletonExtend2_自旋实现 instance = null;
        //这个实现有问题
        while( lock.compareAndSet(false,true) ){
            if( (instance = container.get(CACHE_KEY_PRE+initNumber)) == null ){
                instance = new a_20210220_SingletonExtend2_自旋实现();
                container.put(CACHE_KEY_PRE+initNumber,instance);
            }
            initNumber++;
            if( initNumber > 3 )
                initNumber = 1;
            lock.set(false);
            break;
        }

        return instance;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    a_20210220_SingletonExtend2_自旋实现 instance = a_20210220_SingletonExtend2_自旋实现.getInstance();
                    if (instance == null) {
                        count.incrementAndGet();
                    }
                }
            }, i + "").start();
        }

        System.out.println(container.size());
        System.out.println(container.keySet());
        System.out.println(" null count is " + count.get());
        System.out.println(System.currentTimeMillis() - start);
    }

}
