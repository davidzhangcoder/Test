package a1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class a_20210319_统计累加 {

    static class Data {
        private static final ConcurrentHashMap<String, LongAdder> currentHashMap = new ConcurrentHashMap<String,LongAdder>();

        public void increment(){
            currentHashMap.computeIfAbsent("test", a -> new LongAdder()).increment();

        }

        public int get(){
            return currentHashMap.get("test").intValue();
        }
    }

    //wrong
//    static class Data {
//        private static final ConcurrentHashMap<String,Integer> currentHashMap = new ConcurrentHashMap<String,Integer>();
//
//        public void increment(){
//            if(currentHashMap.get("test")==null)
//                currentHashMap.put("test",1);
//            else
//                currentHashMap.put("test",currentHashMap.get("test")+1);
//        }
//
//        public int get(){
//            return currentHashMap.get("test").intValue();
//        }
//    }

    //wrong
//    static class Data {
//        private static final ConcurrentHashMap<String,Integer> currentHashMap = new ConcurrentHashMap<String,Integer>();
//
//        public void increment(){
//            currentHashMap.computeIfAbsent("test", a -> 1);
//            currentHashMap.computeIfPresent( "test" , (k,v)-> v + 1);
//        }
//
//        public int get(){
//            return currentHashMap.get("test").intValue();
//        }
//    }

    public static void main(String[] args) {
        Data data = new Data();

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    data.increment();
                }
                System.out.println(data.get());
            }).start();
        }
    }
}
