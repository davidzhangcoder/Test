package a1.a_20210318_DomainCache.version5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class a_20210425_DomainCache {

    static AtomicInteger count = new AtomicInteger(0);

    private static int _1M = 1024 * 1024 * 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        -Xmx5m -XX:+PrintGCDetails

//        jstat -gcutil 3324 1000   意思是每1000毫秒查询一次进程号为3324 的JVM的GC垃圾回收的情况

//        CacheUtil cacheUtil = new CacheUtil();

        long meme_free = Runtime.getRuntime().freeMemory() / _1M;
        System.out.println(meme_free + "M");

        ExecutorService executorService = Executors.newFixedThreadPool(11);

//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                CompletableFuture<List<Integer>> cf1 = CompletableFuture.supplyAsync(() -> {
//                    return cacheUtil.getProduct();
//                }, executorService);
//                CompletableFuture<List<String>> cf2 = CompletableFuture.supplyAsync(() -> {
//                    return cacheUtil.getName();
//                }, executorService);
////                System.out.println("start");
//                try {
//                    System.out.println(Thread.currentThread().getName() + " : " + cf2.get());
//                    System.out.println(Thread.currentThread().getName() + " : " + cf1.get());
//                    //executorService.shutdown();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            },"T"+i).start();
//
//            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//
//        }
////        executorService.shutdown();

//        while ( true ) {
//            System.out.println(cacheUtil.getPrivilege());
//            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//        }

        while ( true )
        {
            for (int i = 0; i < 1; i++) {
                new Thread(()->{
//                    System.out.println(Thread.currentThread().getName() + " : product : " + CacheUtil.getProduct());

//                    System.out.println(Thread.currentThread().getName() + " : name : " + CacheUtil.getName());
//                    System.out.println(Thread.currentThread().getName() + " : privilege : " + CacheUtil.getPrivilege());

                    System.out.println(Thread.currentThread().getName() + " : MassiveData" + CacheUtil.getMassiveData());
                },"T"+count.getAndIncrement()).start();
            }
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
            //System.out.println("------");
        }

    }

}
