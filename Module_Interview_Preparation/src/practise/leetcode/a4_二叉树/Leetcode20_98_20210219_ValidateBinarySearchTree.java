package practise.leetcode.a4_二叉树;

import java.util.ArrayList;
import java.util.List;

public class Leetcode20_98_20210219_ValidateBinarySearchTree {

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

    private void inOrder(TreeNode root, List<Integer> result) {
        if ( root == null )
            return;

        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
    }

    public boolean isValidBST(TreeNode root) {
        boolean isBST = true;
        List<Integer> result = new ArrayList<>();

        inOrder(root,result);

        for (int i = 0; i < result.size()-1; i++) {
            if(result.get(i).intValue()>=result.get(i+1).intValue())
                isBST=false;
        }
        return isBST;
    }

}
