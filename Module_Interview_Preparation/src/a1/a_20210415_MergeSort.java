package a1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class a_20210415_MergeSort {

    private static void mergeSort( int[] data, int from , int to ) {
        if( to - from > 0 ) {
            int mid = from + (to-from)/2;
            mergeSort(data , from , mid );
            mergeSort(data , mid+1 , to );

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

    public static void main(String[] args) {
//        int[] a = new int[]{2 , 3 , 1, 1, 2, 5 ,7};
//        mergeSort(a, 0 , a.length-1);
//        System.out.println(Arrays.toString(a));

        int limit = 100000000;
        Random random = new Random();

        int[] testData1 = new int[limit];
        for (int i = 0; i < limit; i++) {
            testData1[i] = random.nextInt(limit);
        }
        int[] testData2 = testData1.clone();
        int[] testData3 = testData1.clone();

        long s1 = System.currentTimeMillis();
        mergeSort(testData1, 0 , testData1.length-1);
        long e1 = System.currentTimeMillis();
        System.out.println("Merge Sort: " + (e1-s1) );
//        System.out.println(Arrays.toString(testData1));

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        a_20210415_MergeSort_ForkJoin.MergeSortForkJoin t = new a_20210415_MergeSort_ForkJoin.MergeSortForkJoin(testData2, 0, testData2.length - 1);
        long s2 = System.currentTimeMillis();
        forkJoinPool.invoke(t);
        long e2 = System.currentTimeMillis();
        System.out.println("Merge Sort ForkJoin: " + (e2-s2) );

        long s3 = System.currentTimeMillis();
        Arrays.sort(testData3);
        long e3 = System.currentTimeMillis();
        System.out.println("Arrays.sort(testData3): " + (e3-s3) );
//        System.out.println(Arrays.toString(testData3));

        boolean b1 = false;
        boolean b2 = false;
        for (int i = 0; i < testData3.length; i++) {
            if(testData1[i] != testData3[i]) {
                System.out.println("i = " + i);
                b1 = true;
                break;
            }

            if(testData2[i] != testData3[i]) {
                b2 = true;
                break;
            }
        }

        if ( b1 == true )
            System.out.println("Merge Sort has error");
        else
            System.out.println("Merge Sort success");

        if ( b2 == true )
            System.out.println("Merge Sort ForkJoin has error");
        else
            System.out.println("Merge Sort ForkJoin success");
    }

}
