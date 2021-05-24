package practise.leetcode.a6_栈和队列.单调栈;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode_43_496_20210321_NextGreaterElementI {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<Integer>();
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();

        for (int i = 0; i < nums2.length; i++) {
            m.put(nums2[i],-1);
            while(!s.isEmpty() && nums2[s.peek()] < nums2[i]) {
                m.put(nums2[s.pop()],nums2[i]);
            }
            s.push(i);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = m.get(nums1[i]);
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] ints = nextGreaterElement(new int[]{2}, new int[]{1});
        System.out.println(Arrays.toString(ints) );
    }

}
