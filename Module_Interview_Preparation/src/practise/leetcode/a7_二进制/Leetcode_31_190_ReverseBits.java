package practise.leetcode.a7_二进制;

public class Leetcode_31_190_ReverseBits {

    public static int reverseBits(int n) {
        int res =0;
        for (int i = 0; i < 32; i++) {
            if( (n&1) == 1 ){
                res = (res<<1)+1;
            } else {
                res = (res<<1);
            }
            n = n >> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 57;
//        String s = Integer.toBinaryString(n);
//        int res = 1;
//
//        System.out.println(res&0);
//        System.out.println(Integer.toBinaryString((n & 1)<<31));
//        System.out.println(Integer.toBinaryString(n>>1));

        System.out.println( Integer.toBinaryString(n) );
        System.out.println( Integer.toBinaryString(reverseBits(n)) );
    }

}
