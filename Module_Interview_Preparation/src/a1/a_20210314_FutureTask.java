package a1;

import java.util.concurrent.*;

public class a_20210314_FutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("running");
                Thread.sleep(2000);
                return "this is result";
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();

        FutureTask<String> futureTask = new FutureTask<String>(callable);
//        futureTask.run();
        executor.submit(futureTask);

//        if( futureTask.isDone() )
            System.out.println(futureTask.get() + " : " + futureTask.isDone());

        executor.submit(futureTask);
        System.out.println(futureTask.get() + " : " + futureTask.isDone());

    }

}
