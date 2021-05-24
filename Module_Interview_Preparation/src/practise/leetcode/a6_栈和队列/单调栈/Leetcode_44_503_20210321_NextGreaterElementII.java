package practise.leetcode.a6_栈和队列.单调栈;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode_44_503_20210321_NextGreaterElementII {

    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> s = new Stack<Integer>();
        int[] a = new int[nums.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            while(!s.isEmpty() && nums[s.peek()]<nums[i]){
                a[s.pop()] = nums[i];
            }
            s.push(i);
        }
        if(!s.isEmpty()){
            for (int i = 0; i < nums.length; i++) {
                while(!s.isEmpty() && nums[s.peek()]<nums[i]){
                    Integer pop = s.pop();
                    if(a[pop]==-1)
                        a[pop] = nums[i];
                }
                s.push(i);
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString( nextGreaterElements(new int[]{1,2,1}) ) );
    }

}
