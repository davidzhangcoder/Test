package practise.leetcode.a5_链表;

public class Leetcode_22_92_20210221_ReverseLinkedListII {

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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(501);
        dummy.next = head;
        ListNode node=head, temp = dummy;
        int count = right - left;
        left--;
        while( left > 0 ) {
            head = head.next;
            node = node.next;
            temp = temp.next;
            left --;
        }


        while( count > 0 ) {
            head = node.next;
            node.next = node.next.next;
            head.next = temp.next;
            temp.next = head;
            count--;
        }

        head = dummy.next;
        dummy.next = null;
        return head;
    }

}
