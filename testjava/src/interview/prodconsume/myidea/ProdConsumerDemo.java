package interview.prodconsume.myidea;

public class ProdConsumerDemo {

    //2个线程
//    public static void main(String[] args) {
//        AirCondition airCondition = new AirCondition();
//
//        new Thread(() ->{
//            for (int i = 1; i <= 10 ; i++) {
//                try {
//                    airCondition.increment();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        } , "A").start();
//
//        new Thread(() ->{
//            for (int i = 1; i <= 10 ; i++) {
//                try {
//                    airCondition.decrement();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        } , "B").start();
//    }

    //大于2个线程
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();

        for (int j = 0; j < 4; j++) {
            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        airCondition.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "A").start();

            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        airCondition.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "B").start();
        }
    }


}
