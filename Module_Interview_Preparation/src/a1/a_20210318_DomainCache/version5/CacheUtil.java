package a1.a_20210318_DomainCache.version5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CacheUtil {

    private static Cache<List<Integer>> productCache = new Cache<List<Integer>>("product" , 30 , TimeUnit.SECONDS) {
        @Override
        public List<Integer> initial() {
//            System.out.println(2 + Thread.currentThread().getName());
//            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
//            System.out.println(3);
            return Arrays.asList(ThreadLocalRandom.current().nextInt(25),ThreadLocalRandom.current().nextInt(25));
        }
    };

    private static Cache<List<String>> nameCache = new Cache<List<String>>("name") {
        @Override
        public List<String> initial() {
//            System.out.println("a");
//            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//            System.out.println("b");
            return Arrays.asList("jack","tom");
        }
    };

    private static Cache<List<Integer>> privilegeCache = new Cache<List<Integer>>("privilege" , 10 , TimeUnit.SECONDS ) {
        @Override
        public List<Integer> initial() {
//            System.out.println("c");
//            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
//            System.out.println("b");
            return Arrays.asList(ThreadLocalRandom.current().nextInt(100));
        }
    };

    private static Cache<MassiveData> massiveDataCache = new Cache<MassiveData>("massiveData" , 5 , TimeUnit.SECONDS) {
        @Override
        public MassiveData initial() {
            return new MassiveData();
        }
    };

    public static List<Integer> getProduct() {
//            System.out.println("getProduct");
            return productCache.get();
    }

    public static List<String> getName() {
//            System.out.println("getName");
            return nameCache.get();
    }

    public static List<Integer> getPrivilege() {
//            System.out.println("getPrivilege");
        return privilegeCache.get();
    }

    public static MassiveData getMassiveData() {
        return massiveDataCache.get();
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
