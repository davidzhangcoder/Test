package practise.leetcode.a7_二进制;

public class Leetcode_33_201_BitwiseANDOfNumbersRange {

    public static int rangeBitwiseAnd(int left, int right) {
        int res =left;
        for (long i = left; i <= right; i++) {
            res = res & (int)i;
            if(res==0)
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(2147483646&2147483646));
        System.out.println(2147483646&2147483646&2147483647);
        System.out.println(Integer.toBinaryString(2147483647));

        System.out.println(rangeBitwiseAnd(2147483646, 2147483647));
    }

}
