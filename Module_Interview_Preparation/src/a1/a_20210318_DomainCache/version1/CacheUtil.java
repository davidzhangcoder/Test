package a1.a_20210318_DomainCache.version1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class CacheUtil {

    private static Cache<List<Integer>> productCache = new Cache<List<Integer>>("product" , 2 , TimeUnit.SECONDS) {
        @Override
        public List<Integer> initial() {
//            System.out.println(2);
            LockSupport.parkNanos(TimeUnit.NANOSECONDS.toNanos(100));
//            System.out.println(3);
            return Arrays.asList(ThreadLocalRandom.current().nextInt(25),ThreadLocalRandom.current().nextInt(25));
        }
    };

    private static Cache<List<String>> nameCache = new Cache<List<String>>("name") {
        @Override
        public List<String> initial() {
//            System.out.println("a");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//            System.out.println("b");
            return Arrays.asList("jack","tom");
        }
    };

    private static Cache<List<Integer>> privilegeCache = new Cache<List<Integer>>("privilege" , 10 , TimeUnit.SECONDS ) {
        @Override
        public List<Integer> initial() {
//            System.out.println("c");
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//            System.out.println("b");
            return Arrays.asList(ThreadLocalRandom.current().nextInt(100));
        }
    };


    public List<Integer> getProduct() {
//            System.out.println("getProduct");
            return productCache.get();
    }

    public List<String> getName() {
//            System.out.println("getName");
            return nameCache.get();
    }

    public List<Integer> getPrivilege() {
//            System.out.println("getPrivilege");
        return privilegeCache.get();
    }
}


//    public List<Object> getProducts(){
//        new CacheEntity<List>() {
//            @Override
//            public List initial() {
//                return null;
//            }
//        }.setKey("Product")
//                .setExpireTime(0);
//        return null;
//    }
//}
