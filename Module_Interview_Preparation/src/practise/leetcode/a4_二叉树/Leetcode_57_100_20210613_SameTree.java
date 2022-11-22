package practise.leetcode.a4_二叉树;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_57_100_20210613_SameTree {

    class TreeNode {
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

    private void preOrder(TreeNode n, List<Integer> l) {
        if( n.left == null && n.right == null ){
            l.add(n.val);
            return;
        }

        l.add(n.val);
        if(n.left != null)
            preOrder(n.left,l);
        else
            l.add(20000);
        if(n.right!=null)
            preOrder(n.right,l);
        else
            l.add(20000);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        if(p!=null)
            preOrder(p,a);
        if(q!=null)
            preOrder(q,b);
        if(a.size()!=b.size())
            return false;
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).intValue()!=b.get(i).intValue())
                return false;
        }
        return true;
    }

}
