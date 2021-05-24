package a1.a_20210318_DomainCache.version1;

import java.util.concurrent.*;

public abstract class Cache<T> {

    //定时刷新，不够容量删除，exception处理

    private static final ConcurrentHashMap<String, Future<?>> map = new ConcurrentHashMap<String,Future<?>>();

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);

    private String key;

    private long expireTime;

    private TimeUnit expireTimeUnit;

    public abstract T initial();

    public Cache(String key) {
        //System.out.println(this.getClass().getTypeName());
        //this.key = key;
        this(key,0);
    }

    public Cache(String key, long expireTime) {
        //System.out.println(this.getClass().getTypeName());
        //this.key = key;
        this(key,expireTime, TimeUnit.SECONDS);
    }

    public Cache(String key, long expireTime, TimeUnit expireTimeUnit) {
        //System.out.println(this.getClass().getTypeName());
        this.key = key;
        this.expireTime = expireTime;
        this.expireTimeUnit = expireTimeUnit;
    }

    public Cache setExpireTime(long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    private void add(long expireTime){

        //似乎FutureTask 不能很好的和 ScheduledThreadPoolExecutor
        //(1)FutureTask运行完成后，state会变成completed,再用scheduleWithFixedDelay()是不会执行的
        //(2)如用schedule(),要等FutureTask运行完之后，再放到map中，似乎做不到这一点
        if( this.expireTime!=0 ) {
            FutureTask<T> futureTask = new FutureTask<T>(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    add( Cache.this.expireTime);
                    return  initial();
                }
            });

            map.put(this.key,futureTask);

            scheduledThreadPoolExecutor.schedule( futureTask ,expireTime, expireTimeUnit);
        }
        else {
            FutureTask<T> futureTask = new FutureTask<T>(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return  initial();
                }
            });

            map.put(this.key,futureTask);

            scheduledThreadPoolExecutor.submit(futureTask);
        }


////        futureTask.run();
    }

    public T get() {
        if( map.get(this.key) == null ){
            synchronized (this) {
                if( map.get(this.key) == null )
                    add(0);
            }
        }

        try {
            return (T) map.get(this.key).get();
        } catch (InterruptedException | ExecutionException e1) {
            map.remove(this.key);
            throw new RuntimeException("Initial Cache Error");
        }
    }

    public static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }
}
