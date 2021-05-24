package a1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class a_20210315_Unsafe {

    static class Data {
        private int value = 0;
    }

    static class Count {
        private static Unsafe unsafe;

        private volatile int value = 0;
        private static long valueOffset = 0;

        static {
            try {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                unsafe = (Unsafe) theUnsafe.get(null);
                valueOffset = unsafe.objectFieldOffset
                        (Count.class.getDeclaredField("value"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        private void incrment() {
            while( !unsafe.compareAndSwapInt(this, valueOffset, value, value+1) ){
            }
        }

        private int getValue(){
           return value;
        }
    }

    private static void test() throws NoSuchFieldException, IllegalAccessException {
//        Unsafe.getUnsafe();

        //测试如何取得Unsafe类
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        theUnsafe.get(null);
    }

    private static void test1() throws NoSuchFieldException, IllegalAccessException {
        //测试Field.get(null),如果作用于instance variable会报NullPointException
        Field theUnsafe = Data.class.getDeclaredField("value");
        theUnsafe.setAccessible(true);
        theUnsafe.get(null);
    }

    private static void test2() throws InterruptedException {
        //测试Count类的increment()方法
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        Count count = new Count();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    count.incrment();
                }
                countDownLatch.countDown();
                System.out.println(count.getValue());
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println(count.getValue());
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
//        test1();
        test2();

//        int a = 10;
//        int b = a;
//        b = b + 5;
//        System.out.println(a);

        System.out.println("Main");
    }
}
