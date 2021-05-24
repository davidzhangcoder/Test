package practise.leetcode.a4_二叉树;

public class Leetcode_51_109_20210405_ConvertSortedListToBinarySearchTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

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


    public TreeNode sortedListToBST(ListNode head) {
        ListNode s1 = head;
        ListNode s2 = head;

        TreeNode temp = new TreeNode(s1.val,null,null);
        TreeNode root = temp;

        while( s2 != null && s2.next != null ){
            s1=s1.next;
            s2=s2.next.next;

            root = new TreeNode(s1.val,null,null);
            root.left = temp;
            temp = root;
        }

        s1 = s1.next;
        temp = root;
        while(s1!=null) {
            TreeNode s = new TreeNode(s1.val,null,null);
            temp.right = s;
            temp = temp.right;
            s1 = s1.next;
        }

        return root;
    }

}
