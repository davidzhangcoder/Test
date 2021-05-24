package testjava;

public class TestTryFinal_20210517 {
    public static void main(String[] args) {
        dotest1();
    }

    private static void dotest1() {
        try{
            System.out.println("dotest1");
            dotest2();
        }
        finally {
            System.out.println("dotest1 - finally");
        }
    }

    private static void dotest2() {
        System.out.println("dotest2");
    }
}
