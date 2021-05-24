package a1;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

public class a_20210412_1_ForkJoin_CountedCompleter {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        AddTask addTask = new AddTask(null, 1, 4);

        forkJoinPool.submit(addTask);
        addTask.join();
        System.out.println(addTask.getRawResult());

//        forkJoinPool.invoke(addTask);
//        addTask.join();
//        System.out.println(addTask.getRawResult());

//        addTask.invoke();
    }
}

class AddTask extends CountedCompleter<Integer> {
    private int from;

    private int to;

    private int subSum;

    AddTask sumTask1;
    AddTask sumTask2;

    public AddTask(CountedCompleter countedCompleter, int from, int to) {
        super(countedCompleter);
        this.from = from;
        this.to = to;
    }

    @Override
    public void compute() {
        System.out.println(Thread.currentThread().getName() + " : " + from + " - " + to);
        Integer r1=null;
        Integer r2=null;
        int sum = 0;
        if( to - from > 2 ) {
            int mid = from + (to - from) / 2;

            addToPendingCount(1);

            sumTask1 = new AddTask(this, from, mid);
            sumTask2 = new AddTask(this,mid+1,to);

            sumTask1.fork();
            sumTask2.fork();

//            sumTask1.join();

        }
        else {
            for (int i = from; i <= to; i++) {
                sum = sum + i;
            }

            this.subSum =sum;
            tryComplete();
        }

        //System.out.println("pending: " + this.getPendingCount());
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        AddTask a = (AddTask) caller;
        if(sumTask1!=null && sumTask2!= null)
            subSum = sumTask1.getRawResult() + sumTask2.getRawResult();

        if( this == caller )
        {
            System.out.println(a.from + " , " + a.to + " , " + a.subSum);
        }
    }

    @Override
    public Integer getRawResult() {
        return this.subSum;
    }
}
