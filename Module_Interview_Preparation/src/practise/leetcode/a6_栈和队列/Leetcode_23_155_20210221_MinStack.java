package practise.leetcode.a6_栈和队列;

import java.util.PriorityQueue;
import java.util.Stack;

public class Leetcode_23_155_20210221_MinStack {

    class MinStack {

        private Stack<Integer> stack;

        private PriorityQueue<Integer> pq;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<Integer>();
            pq = new PriorityQueue<Integer>();
        }

        public void push(int x) {
            stack.push(x);
            pq.add(x);
        }

        public void pop() {
            pq.remove(stack.pop());
        }

        public int top() {
            return stack.peek().intValue();
        }

        public int getMin() {
            return pq.peek().intValue();
        }
    }

}
