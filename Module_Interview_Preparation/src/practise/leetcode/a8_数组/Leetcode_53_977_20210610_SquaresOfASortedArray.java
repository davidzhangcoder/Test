package practise.leetcode.a8_数组;

import java.util.Arrays;

public class Leetcode_53_977_20210610_SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]<0)
                a++;
            else
                break;
        }
        int[] answer = new int[nums.length];
        int i = 0;
        int b = a-1 , c =a;
        while( b >=0 && c < nums.length ){
            if(Math.abs(nums[b])<nums[c]) {
                answer[i] = nums[b]* nums[b];
                b--;
            } else if(Math.abs(nums[b])>nums[c]){
                answer[i] = nums[c]* nums[c];
                c++;
            }
            else{
                answer[i] = nums[b]* nums[b];
                i++;
                answer[i] = nums[c]* nums[c];
                b--;
                c++;
            }
            i++;
        }

        while(b >=0){
            answer[i] = nums[b]* nums[b];
            b--;
            i++;
        }

        while(c < nums.length){
            answer[i] = nums[c]* nums[c];
            c++;
            i++;
        }

        return answer;
    }

    public int[] sortedSquares1(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i]*nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}
