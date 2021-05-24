package practise.leetcode.a5_链表;

public class Leetcode_25_21_20210223_Merge_Two_Sorted_Lists {

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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode head = result;

//        if(l1==null && l2==null)
//            return result;
//        else if(l1==null)
//            return l2;
//        else if(l2==null)
//            return l1;


        while(l1 != null) {
            if( l2 != null ){
                if( l1.val <= l2.val ) {
                    ListNode[] r = move(result, l1);
                    result = r[0];
                    l1=r[1];
                } else {
                    ListNode[] r = move(result, l2);
                    result = r[0];
                    l2=r[1];
                }
            } else {
                ListNode[] r = move(result, l1);
                result = r[0];
                l1=r[1];
            }
        }

        if(l2 != null)
            result.next = l2;

        result = head.next;
        head.next = null;
        return result;
    }

    private ListNode[] move(ListNode result, ListNode t) {
        result.next = t;
        t= t.next;
        result = result.next;
        result.next = null;
        return new ListNode[]{result, t};
    }

}
