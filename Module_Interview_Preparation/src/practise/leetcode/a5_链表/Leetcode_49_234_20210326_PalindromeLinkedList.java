package practise.leetcode.a5_链表;

import java.util.Deque;
import java.util.LinkedList;

public class Leetcode_49_234_20210326_PalindromeLinkedList {

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

    public boolean isPalindrome(ListNode head) {
        Deque<ListNode> deque = new LinkedList<ListNode>();
        while(head != null) {
            deque.add( head );
            head = head.next;
        }

        while( !deque.isEmpty() ) {
            ListNode f = deque.removeFirst();

            if( !deque.isEmpty() ) {
                ListNode l = deque.removeLast();

                if (f.val != l.val)
                    return false;
            }
        }

        return true;
    }

}
