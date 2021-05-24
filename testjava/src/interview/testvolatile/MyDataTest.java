package interview.testvolatile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyDataTest {



    public static void main(String[] args) throws InterruptedException {


        //尝试测试volatile的指令禁排序
        for (int j = 0; j < 20; j++) {
            MyData myDate = new MyData();
            ExecutorService threadPool = Executors.newFixedThreadPool(2);
            for (int i = 0; i < 1000 ; i++) {
                new Thread( () -> {
                    myDate.method1();
                    myDate.method2();
                } ).start();
            }

            Thread.sleep(100);
            System.out.println();
            System.out.println();
        }

    }
}
