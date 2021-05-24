package testjava.teststatic;

public class TestStatic {
    public static void main(String[] args) {
        TestStaticSub a = new TestStaticSub();
        TestStaticSub b = new TestStaticSub();

        System.out.println(a);
        System.out.println(b);
    }
    private static class TestStaticSub {

    }
}
