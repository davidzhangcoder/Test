package a1;

import java.util.concurrent.TimeUnit;

class Volatile1Data {
    //不加volatile,是没有可见性的, t2线程不会知道number已改变
//    private volatile int number = 0;
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

public class Volatile1 {

    public static void main(String[] args) {

        Volatile1Data d = new Volatile1Data();

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
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                if( d.getNumber() != 0 )
                    System.out.println( "number = " + d.getNumber() );
            }
        },"t2").start();
    }
}
