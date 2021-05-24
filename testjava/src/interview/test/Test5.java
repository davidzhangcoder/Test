package interview.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Test5Data implements Callable<Integer> {

    private int start = 0;
    private int end = 0;

    //编写10个线程，第一个线程从1加到10，第二个线程从11加20…第十个线程从91加到100，最后再把10个线程结果相加

    public Test5Data(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = start+1; i <= end; i++) {
            sum = sum + i;
        }
        return sum;
    }
}

public class Test5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(Runtime.getRuntime().availableProcessors());

        FutureTask<Integer>[] a = new FutureTask[10];

        for (int i = 0; i < 10 ; i++) {
            FutureTask<Integer> x = new FutureTask( new Test5Data(i*10,i*10+10) );
            a[i]=x;
            new Thread(x, String.valueOf(i)).start();
        }

        int sum = 0;
        for (FutureTask<Integer> integerFutureTask : a) {
            //integerFutureTask.isDone()
            sum = sum + integerFutureTask.get().intValue();
        }

        System.out.println(sum);
    }
}
