package a1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class a_20210318_ThreadLocal {
    static class Data{
        private final ThreadLocal<Integer> value = new ThreadLocal<>();

        public Integer getValue() {
            return value.get();
        }

        public void setValue(Integer v) {
            value.set(v);
        }
    }

    public static void main(String[] args) {
        Data data = new Data();


        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        //测试ThreadLocal变量不影响
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                data.setValue(i);
                System.out.println(Thread.currentThread().getName() + " : " + data.getValue());
            }
        },"T1").start();

        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                data.setValue(i);
                System.out.println(Thread.currentThread().getName() + " : " + data.getValue());
            }
        },"T2").start();
    }
}
