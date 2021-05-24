package a1.a_20210318_DomainCache.version3;

import java.util.concurrent.*;

public abstract class Cache<T> {

    //定时刷新，不够容量删除，exception处理

    private static final ConcurrentHashMap<String, FutureTask> map = new ConcurrentHashMap<String,FutureTask>();

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

    private void recurringFutureTask(){

    }

    private FutureTask getFutureTask() {
        System.out.println("getFutureTask");
        FutureTask<T> futureTask = new FutureTask<T>(() -> {
            FutureTask f1 = getFutureTask();
            map.put(key, previousFutureTask);
            previousFutureTask= f1;
            scheduledThreadPoolExecutor.schedule(f1, this.expireTime, this.expireTimeUnit);
            return initial();
        }
        );
        return futureTask;
    }

    private void add(){
        System.out.println("add");
        FutureTask futureTask = getFutureTask();
        previousFutureTask = futureTask;
        map.put(key, previousFutureTask);

        if( expireTime > 0 )
            //first run
            scheduledThreadPoolExecutor.schedule(futureTask, 0, this.expireTimeUnit);
        else
            scheduledThreadPoolExecutor.submit(futureTask);
    }

    public T get() {
        if( map.get(this.key) == null ){
            synchronized (this) {
                if( map.get(this.key) == null )
                    add();
            }
        }

        try {
            return (T) ((FutureTask)map.get(this.key)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }
}
