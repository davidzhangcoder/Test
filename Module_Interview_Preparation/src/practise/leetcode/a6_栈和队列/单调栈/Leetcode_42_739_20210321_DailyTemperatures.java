package practise.leetcode.a6_栈和队列.单调栈;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode_42_739_20210321_DailyTemperatures {

    public static int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] array = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            array[i] = 0;
            int index = 0;
            while(!stack.isEmpty() && T[index = stack.peek().intValue()]< T[i]) {
                array[stack.pop().intValue()] = i - index;
            }
            stack.push(i);
        }
        return array;
    }

    public int[] dailyTemperatures_review1_20210610(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int index = stack.peek().intValue();
            while( temperatures[i] > temperatures[index] ){
                answer[index] = i - index;
                stack.pop();
                index = stack.peek();
            }
            stack.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    //For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0]
}
