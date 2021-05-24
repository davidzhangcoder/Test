package practise.leetcode.a5_链表;

public class Leetcode_17_82_20210217_RemoveDuplicatesFromSortedListII {

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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(1000);
        dummy.next = head;
        head = dummy;
        ListNode n1 = head.next;
        ListNode n2 = head;
        while (n1 != null) {
            if (n1.val == head.next.val) {
                n1 = n1.next;
                n2 = n2.next;
            } else {
                if(head.next != n2) {
                    head.next = n1;
                    n2.next = null;
                    n2 = head;
                } else {
                    head = n2;
                }

            }
        }
        if (head.next != n2)
            head.next = n1;
        return dummy.next;
    }

}
