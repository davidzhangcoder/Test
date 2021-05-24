package practise.leetcode.a5_链表;

import java.util.Stack;

public class Leetcode_40_160_20210320_IntersectionOfTwoLinkedLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stack1 = new Stack<ListNode>();
        Stack<ListNode> stack2 = new Stack<ListNode>();

        while(headA != null) {
            stack1.push(headA);
            headA = headA.next;
        }
        while(headB != null) {
            stack2.push(headB);
            headB = headB.next;
        }

        if( stack1.size() == 0 || stack2.size() == 0 )
            return null;

        ListNode same = null;
        while( !stack1.isEmpty() && !stack2.isEmpty() ){
            headA = stack1.pop();
            headB = stack2.pop();
            if(headA==headB)
                same = headA;
            else
                return same;

        }

        return same;
    }

}
