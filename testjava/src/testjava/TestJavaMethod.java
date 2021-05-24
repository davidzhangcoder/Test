package testjava;

import java.util.ArrayList;
import java.util.List;

public class TestJavaMethod {

    public static void main(String[] args) {
        List a = new ArrayList<Integer>();

        System.out.println( doException() );

        String s = "123 45";

        //System.out.println( String.valueOf(s.toCharArray()) );

        String[] sArray = s.split(" ");

        System.out.println( sArray );

    }

    private static int doException() {
       try{
           System.out.println("1");
           return 2;
       }
       catch( Exception e){
           throw new RuntimeException();
       }
       finally {
           System.out.println("finally");
       }
    }

}
