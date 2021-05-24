package practise.leetcode.a5_链表;

public class Leetcode_39_24_20210320_SwapNodesInPairs {

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


    public ListNode swapPairs(ListNode head) {


        ListNode dummy = new ListNode();
        ListNode dummyHead = dummy;
        ListNode f = null;

        while( head != null ) {
            f = head;
            head = head.next;
            if( head != null ) {
                dummy.next= head;
                head = head.next;
                dummy = dummy.next;
                dummy.next = f;
                f.next=null;
                dummy = dummy.next;
            }
            else
                dummy.next = f;
        }

        f = dummyHead.next;
        dummyHead = null;
        return f;
    }

}
