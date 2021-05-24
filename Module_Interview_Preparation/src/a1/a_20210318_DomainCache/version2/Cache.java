package a1.a_20210318_DomainCache.version2;

import java.util.concurrent.*;

public abstract class Cache<T> {

    //定时刷新，不够容量删除，exception处理

    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String,Object>();

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

        init();
    }

    private void init() {
        get();
    }

    public Cache setExpireTime(long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    private void add(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                initial();
                map.put(Cache.this.key,initial());
            }
        };

        if( expireTime > 0 )
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(runnable, 0 , expireTime, expireTimeUnit);
        else
            scheduledThreadPoolExecutor.submit(runnable);
    }

    public T get() {
        if( map.get(this.key) == null ){
            synchronized (this) {
                if( map.get(this.key) == null )
                    add();
            }
        }

        return (T) map.get(this.key);
    }

    public static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }
}
