package a1;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class a_20210415_MergeSort_ForkJoin {
    static class MergeSortForkJoin extends RecursiveAction {

        private int[] data;

        private int from;

        private int to;

        public MergeSortForkJoin(int[] data, int from, int to) {
            this.data = data;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void compute() {
            if( to - from > 0 ) {
                int mid = from + (to-from)/2;
                MergeSortForkJoin t1 = new MergeSortForkJoin(data, from, mid);
                MergeSortForkJoin t2 = new MergeSortForkJoin(data, mid+1, to);

                t1.fork();
                t2.fork();

                t1.join();
                t2.join();

                int[] temp = new int[to - from + 1];
                int t = 0;
                int i = from;
                int j = mid +1;
                while( i <=  mid && j <= to ) {
                    if( data[i] <= data[j] ){
                        temp[t] = data[i];
                        i++;
                    } else {
                        temp[t] = data[j];
                        j++;
                    }
                    t++;
                }

                while(i <=  mid) {
                    temp[t] = data[i];
                    i++;
                    t++;
                }

                while(j <= to) {
                    temp[t] = data[j];
                    j++;
                    t++;
                }

                for (int i1 = 0; i1 < temp.length; i1++) {
                    data[from+i1] = temp[i1];
                }

            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2 , 3 , 1, 1, 2, 5 ,7};

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        MergeSortForkJoin t = new MergeSortForkJoin(a, 0, a.length - 1);
        forkJoinPool.invoke(t);

        System.out.println(Arrays.toString(a));
    }
}
