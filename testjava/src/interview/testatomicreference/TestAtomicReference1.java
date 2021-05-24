package interview.testatomicreference;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class Data1 {
    volatile Integer a = 0;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }
}

public class TestAtomicReference1 {

    public static void main(String[] args) throws InterruptedException {
        Data1 data1 = new Data1();

        //是对Object中的field做CompareAndSet
        AtomicReferenceFieldUpdater<Data1,Integer> updater
                = AtomicReferenceFieldUpdater.newUpdater(Data1.class,Integer.class,"a");

        for(int k = 0 ; k < 100 ;k++) {
            for (int i = 0; i < 100; i++) {

                Thread thread = new Thread(() -> {
                    for (int j = 0; j < 10000; j++) {
                        updater.getAndAccumulate(data1, 1, (a, b) -> {
                            return a + b;
                        });
                    }
                }, "T" + i);
                thread.start();


            }

            Thread.sleep(3000);
            System.out.println(data1.getA());
        }
    }

}
