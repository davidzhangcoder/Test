package interview.testvolatile;

public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();

        //测试volatile不保证一致性
        for (int j = 0; j <30 ; j++) {
            new Thread( () -> {
                for (int i = 0; i < 10000; i++) {
                    myData.number++;
                    myData.atomicInteger.getAndIncrement();
                }
            } ).start();

        }

        while(Thread.activeCount() > 2) {
            Thread.yield();
        }

//        Thread.sleep(3000 );

        System.out.println(Thread.activeCount() + " : " + myData.number + " : " + myData.atomicInteger);
    }

}
