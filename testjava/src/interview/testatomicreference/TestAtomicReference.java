package interview.testatomicreference;

import java.util.concurrent.atomic.AtomicReference;

class Data {
    private int a = 0;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

public class TestAtomicReference {

    public static void main(String[] args) throws InterruptedException {

        Data data = new Data();
        AtomicReference<Data> atomicReferenceData = new AtomicReference<>(data);

        for (int i = 1; i <= 10 ; i++) {
            Thread thread = new Thread(() ->{
                for (int j = 0; j < 1000 ; j++) {

                    //AtomicReference是对整个Object即Data进行CompareAndSet
                    atomicReferenceData.getAndUpdate((a) ->{

                        //1.has concurrent problem
                        //a.setA(a.getA()+1);
                        //return a;

                        Data newData = new Data();
                        newData.setA(a.getA()+1);
                        return newData;
                    });

                    //2.has concurrent problem
                    //data.setA( data.getA() + 1 );
                }
            } , "T"+i);
            thread.start();
        }

        Thread.sleep(3000);
        System.out.println(atomicReferenceData.get().getA());
    }

}
