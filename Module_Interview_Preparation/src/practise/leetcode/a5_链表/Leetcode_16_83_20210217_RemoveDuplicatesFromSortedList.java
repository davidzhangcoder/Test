package practise.leetcode.a5_链表;

public class Leetcode_16_83_20210217_RemoveDuplicatesFromSortedList {

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
        if( head == null || head.next == null )
            return head;

        ListNode n1 = head;
        ListNode n2 = head.next;

        while( n2 != null ) {
            if(n1.val == n2.val){
                n2=n2.next;
                n1.next=null;
                n1.next = n2;
            } else {
                n1=n1.next;
                n2=n2.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

    }

}
