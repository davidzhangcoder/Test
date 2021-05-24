package a1;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class a_20210413_1_ForkJoin {
    public static void main(String[] args) {
//        ForkJoinPool pool = ForkJoinPool.commonPool();
//        TestRecusiveAction t1 = new TestRecusiveAction(1, 15);
//        pool.invoke(t1);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        TestCountedCompleter t1 = new TestCountedCompleter(null, 1, 10);
        pool.invoke(t1);
    }
}

class TestCountedCompleter extends CountedCompleter {

    private int from;

    private int to;

    public TestCountedCompleter(CountedCompleter completer, int from, int to) {
        super(completer);
        this.from = from;
        this.to = to;
    }

    @Override
    public void compute() {
        if( to - from > 2) {
            int mid = from + (to-from)/2;
            TestCountedCompleter t1 = new TestCountedCompleter(this, from, mid);
            TestCountedCompleter t2 = new TestCountedCompleter(this, mid+1, to);

            addToPendingCount(0);
            t1.fork();
            t2.fork();

//            t1.join();
        } else {
            for (int i = from; i <= to; i++) {
                System.out.println(i);
            }
        }

        tryComplete();
    }
}

class TestRecusiveAction extends RecursiveAction{

    private int from;

    private int to;

    public TestRecusiveAction(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void compute() {
        if( to - from > 2 ) {
            int mid = from + (to-from)/2;
            TestRecusiveAction t1 = new TestRecusiveAction(from, mid);
            TestRecusiveAction t2 = new TestRecusiveAction(mid+1, to);

            t1.fork();
            t2.fork();

            t1.join();
            t2.join();
        } else {
            for (int i = from; i <= to; i++) {
                System.out.println(i);
            }
        }
    }
}