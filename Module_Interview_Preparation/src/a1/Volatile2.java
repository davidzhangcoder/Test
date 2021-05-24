package a1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Volatile2Data {

    //AtomicInteger中已对value进行了volatile,所以 AtomicInteger 既有 visibility, 也有 atomic (还有 volatile的禁重排序)
    private AtomicInteger number = new AtomicInteger(0);

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }
}

public class Volatile2 {

    public static void main(String[] args) {

        Volatile2Data d = new Volatile2Data();

        new Thread(()->{
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                d.setNumber( d.getNumber() + 1 );
            }
        },"t1").start();

        new Thread(()->{
            while(true) {
                if( d.getNumber() != 0 )
                    System.out.println( "number = " + d.getNumber() );
            }
        },"t2").start();
    }
}
