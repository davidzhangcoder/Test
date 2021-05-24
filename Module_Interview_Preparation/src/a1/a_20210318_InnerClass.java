package a1;

public class a_20210318_InnerClass {

    private int i;

    class Inner {
        private int a = i;
    }
    static class InnerStatic{
        private int b;

        //wrong
        //private int c = i;
    }

    private void test(){
        Inner inner = new Inner();
        InnerStatic innerStatic = new InnerStatic();
    }

    private static void testStatic() {
        //static
        //Inner inner = new Inner();
        InnerStatic innerStatic = new InnerStatic();
    }

    public static void main(String[] args) {
        //wrong
        //Inner inner = new Inner();

        a_20210318_InnerClass a = new a_20210318_InnerClass();
        a_20210318_InnerClass.Inner inner =  a.new Inner();
    }
}
