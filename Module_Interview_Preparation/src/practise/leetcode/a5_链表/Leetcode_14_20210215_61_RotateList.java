package practise.leetcode.a5_链表;

public class Leetcode_14_20210215_61_RotateList {

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

    public ListNode rotateRight(ListNode head, int k) {
        if( head == null || k == 0)
            return head;

        ListNode end = head;
        int count = 1;
        while( end.next != null ) {
            count++;
            end=end.next;
        }

        if( count == 1 )
            return head;

        k = k % count;
        k = count - k;

//        for (int i = 0; i <k ; i++) {
//            end.next = head;
//            head = head.next;
//            end = end.next;
//            end.next = null;
//        }

        ListNode mid = head;
        for (int i = 1; i <k ; i++)
            mid = mid.next;

        end.next = head;
        head = mid.next;
        mid.next = null;

        return head;
    }

    public static void main(String[] args) {

    }

}
