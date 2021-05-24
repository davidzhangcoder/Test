package practise.leetcode.a7_二进制;

public class Leetcode_32_338_CountingBits {

    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 0; i < num+1 ; i++) {
            res[i] = getCount(i);
        }
        return res;
    }

    private int getCount(int num) {
        int count = 0;
        while(num !=0) {
            num = num&(num-1);
            count++;
        }
        return count;
    }

}
