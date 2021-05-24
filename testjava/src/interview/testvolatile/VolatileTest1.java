package interview.testvolatile;

public class VolatileTest1 {

    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();

        //尝试测试count(不是volatile)的"非可见性"
        for (int j = 0; j <2 ; j++) {
            final int i  = j;
            new Thread( () -> {

                if( i == 0 ) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    myData.add(20);
                }
                else if( i == 1) {
//                    while( myData.count != 20){
//
//                    }

                    myData.add(10);
                }




                System.out.println(Thread.currentThread().getName() + " : " + myData.count );
            } ).start();

        }

        while( myData.count != 30){
//            System.out.println( myData.count );
        }

        System.out.println(Thread.activeCount() + " : " + myData.count );
    }

}
