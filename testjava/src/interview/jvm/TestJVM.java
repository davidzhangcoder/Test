package interview.jvm;

public class TestJVM {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024) + "M");
//        System.out.println(Runtime.getRuntime().totalMemory()/(1024*1024) + "M");

//        String a = "";
//        while(true) {
//            a = a + "A";
//        }

        Thread.sleep(30000);

//        Object1 object1 = new Object1();
        while (true) {
            Object2 object2 = new Object2();
            Thread.sleep(100);
        }
    }

}

class Object1 {
    int size = (10 * 1024 * 1024)/2  ;
    int[] nums = new int[size];
    public Object1() {
        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }
    }

}

class Object2 {
    int size = (1 * 1024 * 1024) ;
    int[] nums = new int[size];
    public Object2() {
        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }
    }
}

