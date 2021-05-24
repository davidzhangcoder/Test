package practise.leetcode.a5_链表;

public class Leetcode_21_206_20210221_ReverseLinkedList {

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


    public ListNode reverseList(ListNode head) {
        if( head == null )
            return head;
        ListNode node = head , temp = head;
        while( node.next != null ) {
            temp =head;
            head = node.next;
            node.next = node.next.next;
            head.next = temp;
        }
        return head;
    }


}
