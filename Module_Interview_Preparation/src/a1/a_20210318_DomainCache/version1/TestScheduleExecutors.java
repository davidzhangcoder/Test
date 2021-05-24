package a1.a_20210318_DomainCache.version1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestScheduleExecutors {

    public static void main(String[] args) {
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = Cache.getScheduledThreadPoolExecutor();

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("B");
                return "B";
            }
        });

        scheduledThreadPoolExecutor.scheduleAtFixedRate( futureTask, 0 , 1, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.schedule(()->{
            return 1;
        },1, TimeUnit.SECONDS);

//        scheduledThreadPoolExecutor.scheduleAtFixedRate( ()->{
//            System.out.println("C");
//        }, 0 , 1, TimeUnit.SECONDS);
    }
}
