package interview.testvolatile;

import java.util.concurrent.atomic.AtomicInteger;

public class MyData {

    int count = 0;

    volatile int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    private int a = 0;
    private boolean flag = false;

    public void add( int value) {
        this.count = this.count + value;
    }

    public void method1() {
        a=1;
        flag=true;
    }

    public void method2() {
        if(flag==true) {
            a = a+5;
            System.out.print(a);
        }
    }

}
