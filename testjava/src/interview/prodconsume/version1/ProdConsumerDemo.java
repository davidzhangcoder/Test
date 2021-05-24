package interview.prodconsume.version1;

public class ProdConsumerDemo {

    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();

        new Thread(() ->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    airCondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } , "A").start();

        new Thread(() ->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    airCondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } , "B").start();

        new Thread(() ->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    airCondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } , "C").start();

        new Thread(() ->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    airCondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } , "D").start();

    }

}
