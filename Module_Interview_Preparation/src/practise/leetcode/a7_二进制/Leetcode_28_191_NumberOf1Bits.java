package practise.leetcode.a7_二进制;

public class Leetcode_28_191_NumberOf1Bits {

    public static int hammingWeight(int n) {
        int count = 0;
        if( n == 0 )
            return count;
        while( n != 0 ) {
            n = n & (n-1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
//        int a = 3;
//        System.out.println( a >> 1 );
//        System.out.println( a );
        System.out.println(hammingWeight(-1));
    }

}
