package leetcode;

import java.util.Arrays;

public class L20201019_747_LargestNumberAtLeastTwiceofOthers {

    public static int dominantIndex(int[] nums) {
        if(nums.length==1)
            return 0;

        int[] numsCopied = new int[nums.length];
        System.arraycopy(nums,0,numsCopied,0,nums.length);

        Arrays.sort(numsCopied);
        if( (numsCopied[numsCopied.length-1] != 0 && numsCopied[numsCopied.length-2]==0)
                || (numsCopied[numsCopied.length-2] >0
                && numsCopied[numsCopied.length-1]/numsCopied[numsCopied.length-2] >= 2) ){
            for (int i = 0; i < nums.length ; i++) {
                if( numsCopied[numsCopied.length-1] == nums[i])
                    return i;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        System.out.println(dominantIndex(a));
    }

}
