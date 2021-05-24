package practise.leetcode.a4_二叉树;

import java.util.*;

public class Leetcode24_107_20210223_BinaryTreeLevelOrderTraversalII {

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

    private void levelOrderUpBottom(TreeNode root, List<List<Integer>> result) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while( !q.isEmpty() ) {
            List<Integer> subResult = new ArrayList<>();

            Queue<TreeNode> temp = new LinkedList<TreeNode>(q);
            q.clear();

            while( !temp.isEmpty() ) {
                TreeNode e = temp.remove();
                subResult.add(e.val);
                if(e.left != null)
                    q.add(e.left);
                if(e.right != null)
                    q.add(e.right);
            }

            result.add( subResult);
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null )
            return result;
        levelOrderUpBottom(root,result);
        Collections.reverse(result);
        return result;
    }

}
