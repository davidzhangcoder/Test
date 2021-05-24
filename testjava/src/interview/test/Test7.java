package interview.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Test7Data {
    private volatile int count = 0;

    //三个窗口同时卖票

    public Test7Data(int count) {
        this.count = count;
    }

    public synchronized boolean sale(){
        if(count > 0) {
            count--;
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }
}

public class Test7 {
    public static void main(String[] args) {
        Test7Data test7Data = new Test7Data(1000);
        int[] a = new int[3];

        for (int i = 0; i < 3; i++) {
            final int i1=i;
            new Thread( ()->{
                while(test7Data.getCount()>0) {
                    if( test7Data.sale() ) {
                        a[i1]++;
                        try {
                            Random random = new Random();
                            TimeUnit.MILLISECONDS.sleep((int)random.nextFloat()*100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " : " + a[i1]);
            } , String.valueOf(i)).start();
        }
    }
}
