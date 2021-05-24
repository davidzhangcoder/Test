package practise.leetcode.a4_二叉树;

public class Leetcode11_104_20210213_MaximumDepthOfBinaryTree {

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

    private int divideAndConquer(TreeNode root) {
        if(root == null)
            return 0;
        int left = divideAndConquer(root.left);
        int right = divideAndConquer(root.right);
        return Math.max(left,right) + 1;
    }

    public int maxDepth(TreeNode root) {
        return divideAndConquer(root);
    }

}
