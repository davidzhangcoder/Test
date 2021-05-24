package practise.leetcode.a6_栈和队列.单调栈;

import java.util.Stack;

public class Leetcode_47_42_20210325_TrappingRainWater {

    public static int trap(int[] height){
        Stack<Integer> s = new Stack<Integer>();
        int[] base = new int[height.length];
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            int left = 0;
            while (!s.isEmpty() && height[s.peek()] <= height[i]) {
                left = s.pop();
                setBaseValue(left, i, base ,left);
            }
            s.push(i);
        }

        int j = 0;
        while( !s.isEmpty() )
            j = s.pop();

        s = new Stack<Integer>();
        for (int i = height.length - 1; i >= j; i--) {
            int right = 0;
            while (!s.isEmpty() && height[s.peek()] <= height[i]) {
                right = s.pop();
                setReverseBaseValue(right, i , base ,right);
            }
            s.push(i);
        }

        for (int i = 0 ; i < base.length ; i++) {
            result = result + (height[base[i]] - height[i]);
        }

        return result;
    }

    private static  void setReverseBaseValue(int s, int e, int[] base, int value){
        for (int i = s; i > e; i--) {
            base[i]= value;
        }
        base[e] = e;
    }

    private static  void setBaseValue(int s, int e, int[] base, int value){
        for (int i = s; i < e; i++) {
            base[i]= value;
        }
        base[e] = e;
    }

    //wrong
//    public static int trap(int[] height) {
//        Stack<Integer> s = new Stack<Integer>();
//        int result = 0;
//        for (int i = 0 ; i < height.length ; i++) {
//            int left = 0;
//            while(!s.isEmpty() && height[s.peek()]<height[i]) {
//                left = s.pop();
//            }
//            if( left != 0 )
//                for (int j = left; j < i; j++) {
//                    result = result + (height[left] - height[j]);
//                }
//            s.push(i);
//        }
//
//        if( !s.isEmpty() && s.size() > 1 ) {
//            int right =0;
//            int left = 0;
//            while( !s.isEmpty() )
//                left = s.pop();
//            for (int i = left + 1; i < right; i++) {
//                result = result + (height[right] - height[i]);
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        System.out.println( trap(new int[]{4,2,0,3,2,5} ) );
    }

}
