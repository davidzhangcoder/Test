package practise.leetcode.a5_链表;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode_46_23_20210325_MergeKSortedLists {

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

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val == o2.val)
                    return 0;
                else if( o1.val < o2.val)
                    return -1;
                else
                    return 1;
            }
        });
        for (ListNode list : lists) {
            while( list!= null ) {
                pq.add(list);
                list = list.next;
            }
        }
        ListNode result = null;
        ListNode head = null;
        while( !pq.isEmpty() ){
            if ( result == null ) {
                result = pq.poll();
                head = result;
            }
            else {
                result.next = pq.poll();
                result = result.next;
                result.next = null;
            }
        }
        return head;
    }

}
