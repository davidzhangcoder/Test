package a1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class a_20210314_CompletableFuture {

    //子线程会阻塞在completableFuture.get(),直到completableFuture.complete("Get Value")把值传入
    private static void doTest() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();

        new Thread(()->{
            try {
                System.out.println(completableFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(2000);

        completableFuture.complete("Get Value");
    }

    //supplyAsync会在另一个线程中执行任务
    //cf1,cf2,cf3一共起了三个不同的线程来执行任务
    private static void doTest1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Get Value1";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Get Value2";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Get Value3";
        });

        System.out.println(cf1.get() + " : " + cf2.get() + " : " + cf3.get());
    }

    //cf1的结果是cf2的传入值
    private static void doTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Get Value1";
        });

        CompletableFuture<String> cf2 = cf1.thenCompose(a -> CompletableFuture.supplyAsync(() -> {
            return "Get Value2 -> " + a;
        }));

        System.out.println( cf2.get() );
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //doTest();
//        doTest1();
        doTest2();
    }

}
