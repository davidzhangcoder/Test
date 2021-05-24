package practise.leetcode.a4_二叉树;

public class Leetcode15_124_20210216_BinaryTreeMaximumPathSum {

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

    //有bug
    private static int[] preOrder(TreeNode root) {
        if( root == null )
            return new int[]{0,0};

        int[] left = preOrder(root.left);
        int[] right = preOrder(root.right);

        int r1 = Math.max(left[0]+root.val, right[0]+ root.val);
        int r2 = Math.max(
                Math.max(root.val<0&&root.left==null?root.val:left[1],root.val<0&&root.right==null?root.val:right[1]),
                Math.max(r1,left[0]+right[0]+root.val)
        );
        r2 = Math.max(root.val, r2);

        return new int[]{r1,r2};
    }

    public static int maxPathSum(TreeNode root) {
        if(root.left==null && root.right==null)
            return root.val;
        int[] r = preOrder(root);
        return r[1];
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(-1);
        TreeNode t3 = new TreeNode(-2);

        t1.left = t2;
        t1.right = t3;

        System.out.println( maxPathSum(t1) );
    }

}
