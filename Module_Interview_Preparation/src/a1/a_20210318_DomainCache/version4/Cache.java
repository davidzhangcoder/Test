package a1.a_20210318_DomainCache.version4;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.*;

public abstract class Cache<T> {

    //定时刷新，不够容量删除，exception处理

    private static final ConcurrentHashMap<CacheKey, CacheValue> map = new ConcurrentHashMap<CacheKey, CacheValue>();

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
//            thread.setDaemon(true);
            return thread;
        }
    };

    //todo
    //optimize by using new ScheduledThreadPoolExecutor()
    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10, THREAD_FACTORY);

    private String key;

    private long expireTime;

    private TimeUnit expireTimeUnit;

    private FutureTask<T> previousFutureTask;

    private ReferenceQueue referenceQueue = new ReferenceQueue();

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

        //此处init(),成本太高
        //因为在CacheUtil中声明为static的cache,全都会在同一个线程中初始化，即第一个使用CacheUtil的线程
        //觉得每次使用的时候再init更好
        //init();
    }

    private void init() {
        get();
    }

    public Cache setExpireTime(long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    private Runnable gettCallable(CacheKey cacheKey) {
        return new Runnable() {
            @Override
            public void run() {
                //System.out.println("callable: " + Thread.currentThread().getName());
                T initial = initial();
                CacheValue cacheValue = new CacheValue(initial, referenceQueue);
                map.put(cacheKey, cacheValue);


                    Runnable callable = gettCallable(cacheKey);
                    ScheduledFuture schedule = scheduledThreadPoolExecutor.schedule(callable, expireTime, expireTimeUnit);

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
    }


    private Future<T> reloadCache(CacheKey cacheKey) {
        if (expireTime > 0) {
            Runnable callable = gettCallable(cacheKey);
            //first run - so set delay to 0
            ScheduledFuture schedule = scheduledThreadPoolExecutor.schedule(callable,0, this.expireTimeUnit);
            return schedule;
        } else {
            Runnable callable = gettCallable(cacheKey);
            Future submit = scheduledThreadPoolExecutor.submit(callable);
            return submit;
        }
    }

    public T get() {
        purgeStaleObject();
        CacheKey cacheKey = new CacheKey(this.key, this.referenceQueue);
        if( map.get(cacheKey) == null ){
            synchronized (this) {
                if( map.get(cacheKey) == null ) {
                    //System.out.println("reloadCache1: " + Thread.currentThread().getName());
                    try {
                        //get()会block当前线程，直到取到值
                        reloadCache(cacheKey).get();
//                        System.out.println("get(): " + reloadCache(cacheKey).get());

//                        Future tFuture = reloadCache(cacheKey);
//                        System.out.println("tFuture.isDone(): "+tFuture.isDone());
//                        tFuture.get();
//                        System.out.println("tFuture.isDone(): "+tFuture.isDone());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if( map.get(cacheKey).get() == null ) {
            synchronized (this) {
                if( map.get(cacheKey).get() == null ) {
                    //System.out.println("reloadCache2: " + Thread.currentThread().getName());
                    try {
                        reloadCache(cacheKey).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                else
                    return (T) map.get(cacheKey).get();
            }
        }

        return (T) map.get(cacheKey).get();
    }

    private void purgeStaleObject() {
        System.out.println("map.size() : " + map.size());
        Reference reference = null;
        while( (reference = referenceQueue.poll()) != null  ) {
            System.out.println("purgeStaleObject: " +reference + " : " + reference.get());

        }
    }

    public static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }

}

