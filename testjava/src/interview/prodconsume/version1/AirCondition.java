package interview.prodconsume.version1;

public class AirCondition {

    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        while(num!=0) {
            wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + " " + num);

        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(num==0) {
            wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + " " + num);

        notifyAll();
    }

}
