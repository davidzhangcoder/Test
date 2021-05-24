package interview.testArrayList;

public class Test {


    //1.如在MyData中用RerntrantLock,主线程过了3秒，可以读取getValue()
    //2.如在MyData中用synchronized,主线程过了3秒，不可以读取getValue()，因为还没有拿到锁
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread( () -> {
            System.out.println( Thread.currentThread().getName()  + " before 11" );
            myData.setValue(11);
            System.out.println( Thread.currentThread().getName()  + " after 10 seconds" );
            System.out.println( Thread.currentThread().getName()  + " after 11" );
        }).start();

        System.out.println( Thread.currentThread().getName() + " before main thread sleep" );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( "after main thread sleep" );
        System.out.println( myData.getValue() );
    }

}
