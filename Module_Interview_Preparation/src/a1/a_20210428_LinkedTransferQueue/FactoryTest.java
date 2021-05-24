package a1.a_20210428_LinkedTransferQueue;

import java.util.concurrent.atomic.AtomicInteger;

public class FactoryTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        Factory<Integer> factory = new Factory<>(()->{
            return atomicInteger.getAndIncrement();
        }, ()->{
            return null;
        } , 5 ,5 , 7);

        factory.start();
    }
}
