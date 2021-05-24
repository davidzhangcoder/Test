package a1;

public class a_20210224_Thread_Interrupt {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            while( true ) {
                if( Thread.currentThread().isInterrupted() )
                    System.out.println("Interrupted");
                else
                    System.out.println("Not Interrupt");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Thread.sleep(10);

        t1.interrupt();
    }
}
