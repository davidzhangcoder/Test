package practise.leetcode.a4_二叉树;

public class Leetcode12_110_20210215_BalancedBinaryTree {

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

    private static int[] inOrder(TreeNode root) {
        if( root == null ) {
            return new int[]{0,1};
        }

        int[] left = inOrder(root.left);
        int[] right = inOrder(root.right);

        if( left[1] == 0 || right[1]==0)
            return new int[]{0,0};
        else
            return new int[]{Math.max(left[0],right[0])+1, Math.abs(left[0]-right[0])>1?0:1};
    }

    public boolean isBalanced(TreeNode root) {
        int[] ints = inOrder(root);
        return ints[1]==1?true:false;
    }

    public static void main(String[] args) {

    }

}
