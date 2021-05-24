package practise.leetcode.a5_链表;

public class Leetcode_26_86_20210223_PartitionList {

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

    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;

        ListNode previoushead = null, smaller = null, smallerhead = null;

        ListNode dummy = new ListNode(201);
        dummy.next = head;
        previoushead = dummy;

        while (head != null) {
            if (head.val < x) {
                if (smaller == null) {
                    smaller = head;
                    smallerhead = smaller;

                    head = head.next;
                    previoushead.next = head;
                    smaller.next = null;
                } else {
                    smaller.next = head;

                    head = head.next;
                    previoushead.next = head;
                    smaller = smaller.next;
                    smaller.next = null;
                }
            }
            else {
                head = head.next;
                previoushead = previoushead.next;
            }

        }

        if (smaller != null)
            smaller.next = dummy.next;
        else
            smallerhead = dummy.next;

        dummy.next = null;

        return smallerhead;
    }

}
