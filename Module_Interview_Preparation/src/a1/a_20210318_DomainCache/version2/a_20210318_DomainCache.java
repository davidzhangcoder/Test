package a1.a_20210318_DomainCache.version2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class a_20210318_DomainCache {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheUtil cacheUtil = new CacheUtil();

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

        while ( true ) {
            System.out.println(cacheUtil.getProduct());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }

    }

}
