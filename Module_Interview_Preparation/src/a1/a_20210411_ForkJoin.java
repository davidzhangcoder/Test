package a1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class a_20210411_ForkJoin {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        SumTask sumTask = new SumTask(1,10000);
        Integer result = forkJoinPool.invoke(sumTask);
        System.out.println(result);
    }

}

class SumTask extends RecursiveTask<Integer> {

    private int from;

    private int to;

    public SumTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " : " + from + " - " + to);
        Integer r1=null;
        Integer r2=null;
        int sum = 0;
        if( to - from > 2 ) {
            int mid = from + (to - from) / 2;
            SumTask sumTask1 = new SumTask(from, mid);
            SumTask sumTask2 = new SumTask(mid+1,to);

            sumTask1.fork();
            sumTask2.fork();

            r1 = (Integer) sumTask1.join();
            r2 = (Integer) sumTask2.join();

            sum = r1.intValue() + r2.intValue();
        }
        else {
            for (int i = from; i <= to; i++) {
                sum = sum + i;
            }
        }
        return sum;
    }
}
