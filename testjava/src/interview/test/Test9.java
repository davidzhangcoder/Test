package interview.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Test9Data implements Callable<Integer> {

    // 编写一个Java程序,该程序将启动4个线程,其中3个是掷硬币线程,1个是主线程。
    // 每个掷硬币线程将连续掷出若干次硬币(10次以内,次数随机生成);主线程将打印出正面出现的总次数以及正面出现的概率。

    private int runningCount;


    public Test9Data(int runningCount) {
        this.runningCount = runningCount;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;;
        Random random = new Random();
        for (int i = 0; i < runningCount; i++) {
            boolean nextDouble = random.nextBoolean();
            if(nextDouble)
                count++;
        }
        return count;
    }
}

public class Test9 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer>[] a = new FutureTask[3];
        int total=0;
        int totalTrue=0;

        Random random = new Random();
        for (int i = 1; i <= 3 ; i++) {
            int nextInt = random.nextInt(100);
            total = total + nextInt;
            FutureTask<Integer> futureTask = new FutureTask(new Test9Data(nextInt));
            a[i-1] = futureTask;
            new Thread(futureTask,String.valueOf(i)).start();
        }

        for (FutureTask<Integer> integerFutureTask : a) {
            totalTrue = totalTrue + integerFutureTask.get();
        }

        System.out.println("total=" + total + " totalTrue=" + totalTrue + " ratio=" + (float)totalTrue/(float)total);
    }
}
